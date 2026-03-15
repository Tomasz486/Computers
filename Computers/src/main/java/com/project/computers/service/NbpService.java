package com.project.computers.service;

import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.computers.exception.NbpException;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

@Service
/**
 * Service responsible for communication with NBP API.
 * 
 * Downloads USD exchange rate for a given date. If the rate does not exist
 * (weekend or holiday), the service searches previous working days.
 */
public class NbpService {

	private static final String NpbUrl = "http://api.nbp.pl/api/exchangerates/rates/A/USD/";

	private static final String NpbFormat = "/?format=json";

	private static final String RatesKey = "rates";

	private static final int RatesIndex = 0;

	private static final String MidKey = "mid";

	private static final Logger log = LoggerFactory.getLogger(NbpService.class);

	/**
	 * Downloads USD exchange rate for given date.
	 */
	public BigDecimal downloadUsdRate(LocalDate date) {

		log.info("Downloading USD exchange rate for {}", date);

		LocalDate lastRateData = this.adjustToPreviousWorkingDay(date);

		String url = NpbUrl + lastRateData + NpbFormat;

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		log.debug("NBP API response received");

		HttpResponse<String> response;

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {

			log.error("Error while calling NBP API", e);

			throw new NbpException("Cannot download exchange rate", e);
		}

		JsonObject json = Json.createReader(new StringReader(response.body())).readObject();

		JsonArray rates = json.getJsonArray(RatesKey);
		JsonObject rate = rates.getJsonObject(RatesIndex);

		return rate.getJsonNumber(MidKey).bigDecimalValue();
	}

	/**
	 * Adjust to previous working day.
	 */
	LocalDate adjustToPreviousWorkingDay(LocalDate date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();

		if (dayOfWeek == DayOfWeek.SATURDAY) {
			return date.minusDays(1);
		}
		if (dayOfWeek == DayOfWeek.SUNDAY) {
			return date.minusDays(2);
		}
		return date;
	}
}