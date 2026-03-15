package com.project.computers.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ComputerInput {

	private String name;
	private LocalDate accountingDate;
	private BigDecimal costUsd;

	public ComputerInput() {
	}

	public ComputerInput(String name, LocalDate accountingDate, BigDecimal costUsd) {
		this.name = name;
		this.accountingDate = accountingDate;
		this.costUsd = costUsd;
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
}
