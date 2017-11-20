package com.matera.challenge.college.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportDto {
	
	private String cpf;
	
	private List<SubjectDto> subjects;

	public ReportDto() {}
	
	public ReportDto(String cpf) {
		this.cpf = cpf;
		this.subjects = new ArrayList<SubjectDto>();
	}

	public ReportDto(String cpf, List<SubjectDto> subjects) {
		this.cpf = cpf;
		this.subjects = subjects;
	}
	
	public boolean hasRedGrades() {
		return subjects.stream().anyMatch(s -> s.isRed());
	}

	public void addSubject(SubjectDto subjectDto) {
		subjects.add(subjectDto);
	}

	public List<SubjectDto> getRedGrades() {
		return subjects.stream().filter(s -> s.isRed()).collect(Collectors.toList());
	}

	public String getCpf() {
		return cpf;
	}

	public List<SubjectDto> getSubjects() {
		return subjects;
	}
}
