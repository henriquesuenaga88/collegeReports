package com.matera.challenge.college.rest;

import java.util.List;

import javax.management.ServiceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matera.challenge.college.dtos.DirectMailDto;
import com.matera.challenge.college.services.DirectMailServices;

@RestController
@RequestMapping("/college/directMail")
public class DirectMailController {
	
	@Autowired
	private DirectMailServices directMailServices;

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> sendDirectMail() {
		List<DirectMailDto> failures = null;
		try {
			failures = directMailServices.getStudentFailures();
		} catch (ServiceNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Sorry, Please contact administrator.", HttpStatus.BAD_REQUEST);
		}
		
        return new ResponseEntity<List<DirectMailDto>>(failures, HttpStatus.OK);
	}

}
