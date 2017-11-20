package com.matera.challenge.college.dtos;

public class StudentDto {
	
	private String document;
	
	private String name;
	
	private String address;
	
	private String zipCode;

	public StudentDto() {}
	
	public StudentDto(String document, String name, String address, String zipCode) {
		this.document = document;
		this.name = name;
		this.address = address;
		this.zipCode = zipCode;
	}

	public String getDocument() {
		return document;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getZipCode() {
		return zipCode;
	}
	
}
