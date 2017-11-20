package com.matera.challenge.college.dtos;

public class DirectMailDto {
	
	private String name;
	
	private String address;
	
	private String zipCode;
	
	private Integer quantityGradeFailures;
	
	public DirectMailDto() {}
	
	public DirectMailDto(StudentDto studentDto, int quantityGradeFailures) {
		this.name = studentDto.getName();
		this.address = studentDto.getAddress();
		this.zipCode = studentDto.getZipCode();
		this.quantityGradeFailures = quantityGradeFailures;
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

	public String getMessage() {
		return "Atenção, o(a) aluno(a) " 
				+ getName()
				+ " está com "
				+ getQuantityGradeFailures()
				+ " nota(s) abaixo da média.";
	}

	public Integer getQuantityGradeFailures() {
		return quantityGradeFailures;
	}

}
