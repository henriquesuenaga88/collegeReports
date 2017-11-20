package com.matera.challenge.college.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matera.challenge.college.dtos.StudentDto;
import com.matera.challenge.college.services.StudentServices;

@RestController
@RequestMapping("/students/")
public class StudentController {
	
	@Autowired
	private StudentServices studentServices;

	@RequestMapping(produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.GET)
	public ResponseEntity<?> sendDirectMail() {
		final List<StudentDto> students = studentServices.getAllStudents();
		
        return new ResponseEntity<List<StudentDto>>(students, HttpStatus.OK);
	}
	
	public StudentServices getStudentServices() {
		return studentServices;
	}

	public void setStudentServices(StudentServices studentServices) {
		this.studentServices = studentServices;
	}

}
