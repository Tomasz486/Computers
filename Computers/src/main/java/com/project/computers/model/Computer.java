package com.project.computers.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.project.computers.dto.ComputerInput;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity representing computer purchase.
 * 
 * Stored in database table "computer".
 */
@Entity
public class Computer {

	/**
	 * Entity representing computer purchase.
	 * 
	 * Stored in database table "computer".
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Computer name.
	 */
	@Column(name = "nazwa")
	private String name;

	/**
	 * Accounting date used for currency conversion.
	 */
	@Column(name = "data_ksiegowania")
	private LocalDate accountingDate;

	/**
	 * Cost in USD.
	 */
	@Column(name = "koszt_USD")
	private BigDecimal costUsd;

	/**
	 * Converted cost in PLN.
	 */
	@Column(name = "koszt_PLN")
	private BigDecimal costPln;

	public Computer() {
	}

	public Computer(ComputerInput computerInput, BigDecimal costPln) {
		this.name = computerInput.getName();
		this.accountingDate = computerInput.getAccountingDate();
		this.costUsd = computerInput.getCostUsd();
		this.costPln = costPln;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%d, name='%s']", id, name);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getAccountingDate() {
		return accountingDate;
	}

	public void setAccountingDate(LocalDate accountingDate) {
		this.accountingDate = accountingDate;
	}

	public BigDecimal getCostUsd() {
		return costUsd;
	}

	public void setCostUsd(BigDecimal costUsd) {
		this.costUsd = costUsd;
	}

	public BigDecimal getCostPln() {
		return costPln;
	}

	public void setCostPln(BigDecimal costPln) {
		this.costPln = costPln;
	}

}
