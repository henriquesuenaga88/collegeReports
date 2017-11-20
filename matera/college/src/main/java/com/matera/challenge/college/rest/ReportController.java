package com.matera.challenge.college.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matera.challenge.college.dtos.ReportDto;
import com.matera.challenge.college.services.ReportServices;

@RestController
@RequestMapping("/students/{cpf}/report")
public class ReportController {
	
	@Autowired
	private ReportServices reportServices;

	@RequestMapping(produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.GET)
	public ResponseEntity<?> getReport(@PathVariable String cpf) {
		final ReportDto report = reportServices.getReportBy(cpf);
		
        return new ResponseEntity<ReportDto>(report, HttpStatus.OK);
	}
}
