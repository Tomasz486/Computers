package com.project.computers.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

import com.project.computers.model.Computer;

@XmlRootElement(name = "komputer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ComputerXml {

	@XmlElement(name = "nazwa")
	private String name;

	@XmlElement(name = "data_ksiegowania")
	private String accountingDate;

	@XmlElement(name = "koszt_USD")
	private BigDecimal costUsd;

	@XmlElement(name = "koszt_PLN")
	private BigDecimal costPln;

	public ComputerXml() {
	}

	public ComputerXml(String name, String accountingDate, BigDecimal costUsd, BigDecimal costPln) {
		this.name = name;
		this.accountingDate = accountingDate;
		this.costUsd = costUsd;
		this.costPln = costPln;
	}

	public ComputerXml(Computer computer) {
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

	@Override
	public String toString() {
		return "ComputerXml{" + "name='" + name + '\'' + ", accountingDate='" + accountingDate + '\'' + ", costUsd="
				+ costUsd + '}';
	}
}