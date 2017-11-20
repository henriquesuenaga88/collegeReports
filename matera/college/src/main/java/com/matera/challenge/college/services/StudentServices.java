package com.matera.challenge.college.services;

import static com.matera.challenge.college.commons.utils.DocumentUtils.isEquals;
import static com.matera.challenge.college.commons.utils.ReportsUrls.GET_STUDENTS;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.matera.challenge.college.dtos.ReportDto;
import com.matera.challenge.college.dtos.StudentDto;

@Service
public class StudentServices {

	public List<StudentDto> getAllStudents() {
		return Stream.of(
				new StudentDto("111.111.111-11", "José da Silva", "Rua Amazonas", "11111-111"),
				new StudentDto("222.222.222-22", "Maria Coelho", "Rua Bahia", "22222-222"),
				new StudentDto("333.333.333-33", "João Magalhães", "Rua Goiás", "33333-333")
			).collect(Collectors.toList());
	}
	
	public List<StudentDto> callForAllStudents() {
		final RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<StudentDto>> students = restTemplate
				.exchange(GET_STUDENTS
				, HttpMethod.GET
				, null
				, new ParameterizedTypeReference<List<StudentDto>>(){});
		
		return students.getBody();
	}

	public StudentDto getBy(ReportDto reportDto, List<StudentDto> students) {
		return students.stream()
				.filter(s -> isEquals(s.getDocument(), reportDto.getCpf()))
				.findFirst().get();
	}
}
