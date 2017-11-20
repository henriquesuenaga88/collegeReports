package com.matera.challenge.directmail;

import static com.matera.challenge.college.commons.utils.ReportsUrls.DIRECT_MAIL;
import static com.matera.challenge.college.commons.utils.ReportsUrls.FROM_CPF;
import static com.matera.challenge.college.commons.utils.ReportsUrls.GET_STUDENTS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.matera.challenge.college.dtos.DirectMailDto;
import com.matera.challenge.college.dtos.ReportDto;
import com.matera.challenge.college.dtos.StudentDto;

@RunWith(SpringRunner.class)
public class CollegeApplicationTests {

	@Test
	public void givenRequestToAllStudents_WhenCallService_ThenSucessWithThreeStudents() {
		final RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<StudentDto>> students = restTemplate
				.exchange(GET_STUDENTS
				, HttpMethod.GET
				, null
				, new ParameterizedTypeReference<List<StudentDto>>(){});

		Assert.assertThat(students.getHeaders().getContentType(), is(MediaType.APPLICATION_JSON_UTF8));
		Assert.assertThat(students.getStatusCode(), is(HttpStatus.OK));
		Assert.assertThat(students.getBody(), hasSize(3));
	}

	@Test
	public void givenCpf11111111111_WhenCallService_ThenSucessGettingReportWithNoRedGrades() {
		final RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<ReportDto> reportResponse = restTemplate
				.exchange(GET_STUDENTS + "11111111111" + FROM_CPF, 
				HttpMethod.GET,
				null,
				ReportDto.class);

		Assert.assertThat(reportResponse.getHeaders().getContentType(), is(MediaType.APPLICATION_JSON_UTF8));
		Assert.assertThat(reportResponse.getStatusCode(), is(HttpStatus.OK));
		Assert.assertThat(reportResponse.getBody().getCpf(), equalTo("11111111111"));
		Assert.assertThat(reportResponse.getBody().getSubjects().size(), is(4));
		Assert.assertThat(reportResponse.getBody().getRedGrades().size(), is(0));
	}

	@Test
	public void givenCpf22222222222_WhenCallService_ThenSucessGettingReportWithAllRedGrades() {
		final RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<ReportDto> reportResponse = restTemplate
				.exchange(GET_STUDENTS + "22222222222" + FROM_CPF, 
				HttpMethod.GET,
				null,
				ReportDto.class);

		Assert.assertThat(reportResponse.getHeaders().getContentType(), is(MediaType.APPLICATION_JSON_UTF8));
		Assert.assertThat(reportResponse.getStatusCode(), is(HttpStatus.OK));
		Assert.assertThat(reportResponse.getBody().getCpf(), equalTo("22222222222"));
		Assert.assertThat(reportResponse.getBody().getSubjects().size(), is(4));
		Assert.assertThat(reportResponse.getBody().getRedGrades().size(), is(4));
	}

	@Test
	public void givenCpf333333333333_WhenCallService_ThenSucessGettingReportWithOneRedGrade() {
		final RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<ReportDto> reportResponse = restTemplate
				.exchange(GET_STUDENTS + "33333333333" + FROM_CPF, 
				HttpMethod.GET,
				null,
				ReportDto.class);
		
		Assert.assertThat(reportResponse.getHeaders().getContentType(), is(MediaType.APPLICATION_JSON_UTF8));
		Assert.assertThat(reportResponse.getStatusCode(), is(HttpStatus.OK));
		Assert.assertThat(reportResponse.getBody().getCpf(), equalTo("33333333333"));
		Assert.assertThat(reportResponse.getBody().getSubjects().size(), is(4));
		Assert.assertThat(reportResponse.getBody().getRedGrades().size(), is(1));
	}
	
	@Test
	public void whenCallService_ThenReturnTwoReports() {
		final RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<List<DirectMailDto>> directMailResponse = restTemplate
				.exchange(DIRECT_MAIL, 
				HttpMethod.POST,
				null,
				new ParameterizedTypeReference<List<DirectMailDto>>(){});

		Assert.assertThat(directMailResponse.getHeaders().getContentType(), is(MediaType.APPLICATION_JSON_UTF8));
		Assert.assertThat(directMailResponse.getStatusCode(), is(HttpStatus.OK));
		Assert.assertThat(directMailResponse.getBody(), hasSize(2));
	}
}
