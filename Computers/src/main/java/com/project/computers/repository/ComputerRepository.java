package com.project.computers.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.computers.model.Computer;

/**
 * Repository responsible for database operations on Computer entity.
 */
public interface ComputerRepository extends JpaRepository<Computer, Long> {

	/**
	 * Finds computers by accounting date.
	 */
	Page<Computer> findByAccountingDate(LocalDate accountingDate, Pageable pageable);

	/**
	 * Finds computers whose name contains given string (case insensitive).
	 */
	Page<Computer> findByNameContainingIgnoreCase(String name, Pageable pageable);

	/**
	 * Finds computers by name contains given string (case insensitive) and
	 * accounting date.
	 */
	Page<Computer> findByNameContainingIgnoreCaseAndAccountingDate(String name, LocalDate accountingDate,
			Pageable pageable);
}
