package com.project.computers.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.computers.dto.ComputerDto;
import com.project.computers.model.Computer;
import com.project.computers.repository.ComputerRepository;

/**
 * Service layer containing business logic related to computers.
 */
@Service
public class ComputerService {

	private final DataInitializer dataInitializer;

	private final ComputerRepository computerRepository;

	private final NbpService nbpService;

	private final ComputerXmlService computerXmlService;

	public ComputerService(DataInitializer dataInitializer, ComputerRepository computerRepository,
			NbpService nbpService, ComputerXmlService computerXmlService) {
		this.dataInitializer = dataInitializer;
		this.computerRepository = computerRepository;
		this.nbpService = nbpService;
		this.computerXmlService = computerXmlService;
	}

	/**
	 * Service layer containing business logic related to computers.
	 * 
	 * Responsibilities: - filtering and pagination - currency conversion (USD →
	 * PLN) - saving data to database - generating XML invoice
	 */
	public Page<ComputerDto> GetComputers(String name, LocalDate accountingDate, Pageable pageable) throws Exception {

		dataInitializer.initDatabase(computerRepository, nbpService, computerXmlService);

		Page<Computer> page;

		boolean hasName = name != null && !name.isBlank();
		boolean hasDate = accountingDate != null;

		if (hasName && hasDate) {
			page = computerRepository.findByNameContainingIgnoreCaseAndAccountingDate(name, accountingDate, pageable);
		} else if (hasName) {
			page = computerRepository.findByNameContainingIgnoreCase(name, pageable);
		} else if (hasDate) {
			page = computerRepository.findByAccountingDate(accountingDate, pageable);
		} else {
			page = computerRepository.findAll(pageable);
		}

		return page.map(ComputerDto::new);
	}
}
