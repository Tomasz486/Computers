package com.project.computers.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "komputery")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvoiceXml {

	@XmlElement(name = "komputer")
	private List<ComputerXml> computers;

	public InvoiceXml() {
	}

	public List<ComputerXml> getComputers() {
		return computers;
	}

	public void setComputers(List<ComputerXml> computers) {
		this.computers = computers;
	}
}