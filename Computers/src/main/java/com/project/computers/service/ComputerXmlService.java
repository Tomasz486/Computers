package com.project.computers.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.computers.dto.ComputerInput;
import com.project.computers.model.Computer;
import com.project.computers.xml.ComputerXml;
import com.project.computers.xml.InvoiceXml;

/**
 * Service responsible for generating XML invoice.
 * 
 * Converts list of computers into XML structure.
 * 
 */
@Service
public class ComputerXmlService {

	private final String JsonDataFileName = "komputery.json";

	private final String xmlInvoiceOutputFileName = "faktura.xml";

	private static final Logger log = LoggerFactory.getLogger(ComputerService.class);

	/**
	 * Read XML file with computers.
	 */
	public List<ComputerInput> ReadXml() throws StreamReadException, DatabindException, IOException {

		ObjectMapper mapper = new ObjectMapper();

		mapper.registerModule(new JavaTimeModule());

		mapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		log.debug("Processing file: {}", JsonDataFileName);

		return mapper.readValue(new ClassPathResource(JsonDataFileName).getInputStream(),
				new TypeReference<List<ComputerInput>>() {
				});
	}

	/**
	 * Saves invoice XML file to disk.
	 */
	public void SaveXml(Iterable<Computer> computers) {
		InvoiceXml invoiceXml = new InvoiceXml();

		List<ComputerXml> computerXmlList = new ArrayList<ComputerXml>();

		for (Computer computer : computers) {
			computerXmlList.add(new ComputerXml(computer));
		}

		invoiceXml.setComputers(computerXmlList);

		try {
			JAXBContext context = JAXBContext.newInstance(InvoiceXml.class);

			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			marshaller.marshal(invoiceXml, new File(xmlInvoiceOutputFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
