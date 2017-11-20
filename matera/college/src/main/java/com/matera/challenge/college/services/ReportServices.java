package com.matera.challenge.college.services;

import static com.matera.challenge.college.commons.utils.ReportsUrls.FROM_CPF;
import static com.matera.challenge.college.commons.utils.ReportsUrls.GET_STUDENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.matera.challenge.college.commons.utils.DocumentUtils;
import com.matera.challenge.college.dtos.ReportDto;
import com.matera.challenge.college.dtos.SubjectDto;
import com.matera.challenge.college.enums.SubjectType;

@Service
public class ReportServices {
	
	public List<ReportDto> getAllReports() {
		List<SubjectDto> subjects1 = new ArrayList<SubjectDto>();
		subjects1.add(new SubjectDto(SubjectType.HISTORY, 7.0));
		subjects1.add(new SubjectDto(SubjectType.LANGUAGE, 7.0));
		subjects1.add(new SubjectDto(SubjectType.MATH, 7.0));
		subjects1.add(new SubjectDto(SubjectType.SCIENCE, 7.0));
		
		List<SubjectDto> subjects2 = new ArrayList<SubjectDto>();
		subjects2.add(new SubjectDto(SubjectType.HISTORY, 6.9));
		subjects2.add(new SubjectDto(SubjectType.LANGUAGE, 6.9));
		subjects2.add(new SubjectDto(SubjectType.MATH, 6.9));
		subjects2.add(new SubjectDto(SubjectType.SCIENCE, 6.9));
		
		List<SubjectDto> subjects3 = new ArrayList<SubjectDto>();
		subjects3.add(new SubjectDto(SubjectType.HISTORY, 9.0));
		subjects3.add(new SubjectDto(SubjectType.LANGUAGE, 3.7));
		subjects3.add(new SubjectDto(SubjectType.MATH, 8.0));
		subjects3.add(new SubjectDto(SubjectType.SCIENCE, 10.0));
		
		return Stream.of(
				new ReportDto("11111111111", subjects1), 
				new ReportDto("22222222222", subjects2), 
				new ReportDto("33333333333", subjects3)
			).collect(Collectors.toList());
	}
	
	public ResponseEntity<ReportDto> callForReport(String cpf) {
		return new RestTemplate()
				.exchange(GET_STUDENTS + cpf + FROM_CPF, 
				HttpMethod.GET,
				null,
				ReportDto.class);
	}
	
	public ReportDto getReportBy(String cpf) {
		return getAllReports()
			.stream()
			.filter(r -> DocumentUtils.isEquals(r.getCpf(), cpf))
			.findFirst()
			.get();
	}

}
