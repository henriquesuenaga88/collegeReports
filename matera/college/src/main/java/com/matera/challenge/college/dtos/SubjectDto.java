package com.matera.challenge.college.dtos;

import static com.matera.challenge.college.commons.utils.ConfigurationConstants.MINIMUN_GRADE_AVG;

import com.matera.challenge.college.enums.SubjectType;

public class SubjectDto {

	private SubjectType type;
	
	private Double grade;

	public SubjectDto() {}
	
	public SubjectDto(SubjectType type, Double grade) {
		this.type = type;
		this.grade = grade;
	}
	
	public boolean isRed() {
		return grade < MINIMUN_GRADE_AVG;
	}
	
	public SubjectType getType() {
		return type;
	}
	
	public Double getGrade() {
		return grade;
	}
}
