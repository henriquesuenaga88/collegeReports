package com.matera.challenge.college.services;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;

import javax.management.ServiceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matera.challenge.college.dtos.DirectMailDto;
import com.matera.challenge.college.dtos.ReportDto;
import com.matera.challenge.college.dtos.StudentDto;

@Service
public class DirectMailServices {

	@Autowired
	private ReportServices reportServices;
	
	@Autowired
	private StudentServices studentServices;
	
	public List<DirectMailDto> getStudentFailures() throws ServiceNotFoundException {
		List<StudentDto> students = null;
		try {
			students = studentServices.callForAllStudents();
		} catch (Exception e) {
			throw new ServiceNotFoundException("There was a problem during students API call. Check the following log for more information.");
		}
		
		return isEmpty(students) ?
				new ArrayList<DirectMailDto>() 
				: buildDirectMailDto(students);
	}

	private List<DirectMailDto> buildDirectMailDto(final List<StudentDto> students) {
		final List<DirectMailDto> failures = new ArrayList<DirectMailDto>();
		students.forEach(s -> {
			final ReportDto reportDto = reportServices.callForReport(s.getDocument()).getBody();
			if (reportDto.hasRedGrades()) {
				failures.add(new DirectMailDto(s, reportDto.getRedGrades().size()));
			}
		});
		
		return failures;
	}

}
