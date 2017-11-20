package com.matera.challenge.college.enums;

public enum SubjectType {

	MATH("M"),
	LANGUAGE("L"),
	SCIENCE("S"),
	HISTORY("H");
	
	private String type;
	
	SubjectType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
