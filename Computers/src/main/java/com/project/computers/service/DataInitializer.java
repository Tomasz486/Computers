package com.project.computers.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.computers.dto.ComputerInput;
import com.project.computers.model.Computer;
import com.project.computers.repository.ComputerRepository;

@Service
/**
 * Initializes database with predefined computers
 */
public class DataInitializer {

	private static final Logger log = LoggerFactory.getLogger(ComputerService.class);

	/**
	 * Initializes database with predefined computers.
	 * 
	 * Steps: 1. Load predefined computers 2. Download USD exchange rate from NBP 3.
	 * Convert USD → PLN 4. Save to database 5. Generate XML invoice
	 */
	public void initDatabase(ComputerRepository computerRepository, NbpService nbpService,
			ComputerXmlService computerXmlService) throws Exception {

		log.info("Checking if database is empty");

		if (computerRepository.count() == 0) {

			List<ComputerInput> computerInputs = computerXmlService.ReadXml();

			for (ComputerInput computerInput : computerInputs) {

				log.info("Downloading usd rate for {}", computerInput.getName());

				BigDecimal rate = nbpService.downloadUsdRate(computerInput.getAccountingDate());

				// convert USD to PLN with rounding to 2 decimal places
				Computer computer = new Computer(computerInput,
						computerInput.getCostUsd().multiply(rate).setScale(2, RoundingMode.HALF_UP));

				computerRepository.save(computer);
			}

			computerXmlService.SaveXml(computerRepository.findAll());
		}
	}
}