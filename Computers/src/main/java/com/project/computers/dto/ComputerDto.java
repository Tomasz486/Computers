package com.project.computers.dto;

import java.math.BigDecimal;

import com.project.computers.model.Computer;

public class ComputerDto {

	private String name;

	private String accountingDate;

	private BigDecimal costUsd;

	private BigDecimal costPln;

	public ComputerDto(Computer computer) {
		this.name = computer.getName();
		this.accountingDate = computer.getAccountingDate().toString();
		this.costUsd = computer.getCostUsd();
		this.costPln = computer.getCostPln();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountingDate() {
		return accountingDate;
	}

	public void setAccountingDate(String accountingDate) {
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
