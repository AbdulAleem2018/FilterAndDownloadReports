package com.a2stech.filterandreports.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ApplicationRegistrationForm {
	
	private String fullName;
	private String email;
	private Long mobileNo;
	private String gender;
	private Long ssn;
	private LocalDate dateOfBirth;

}
