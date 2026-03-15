package com.project.computers.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.computers.dto.ComputerDto;
import com.project.computers.service.ComputerService;

@RestController
public class ComputerController {

	private final ComputerService computerService;

	public ComputerController(ComputerService computerService) {
		this.computerService = computerService;
	}

	@GetMapping("/computers")
	public Page<ComputerDto> getComputers(@RequestParam(required = false) String name,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate accountingDate,
			Pageable pageable) throws Exception {

		return computerService.GetComputers(name, accountingDate, pageable);
	}
}