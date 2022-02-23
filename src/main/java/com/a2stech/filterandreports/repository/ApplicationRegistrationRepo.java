package com.a2stech.filterandreports.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a2stech.filterandreports.entities.ApplicationRegistration;
import com.a2stech.filterandreports.entities.ApplicationRegistrationPK;

public interface ApplicationRegistrationRepo extends JpaRepository<ApplicationRegistration, ApplicationRegistrationPK>{

}
