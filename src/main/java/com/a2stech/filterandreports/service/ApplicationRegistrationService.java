package com.a2stech.filterandreports.service;

import java.util.List;

import com.a2stech.filterandreports.entities.ApplicationRegistration;
import com.a2stech.filterandreports.model.ApplicationRegistrationForm;

public interface ApplicationRegistrationService {
	
	public String saveCitizenDetails(ApplicationRegistrationForm appForm);

	public List<ApplicationRegistration> getAllKESCitizenDetails();

}
