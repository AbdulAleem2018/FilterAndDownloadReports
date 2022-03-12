package com.a2stech.filterandreports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.a2stech.filterandreports.entities.EligibilityDetails;

public interface EligibilityDeterminationRepo extends JpaRepository<EligibilityDetails, Integer>{

	@Query("select ed from EligibilityDetails ed where ed.caseNo = :#{#caseNumber}")
	public EligibilityDetails getEligibilityDetailsByUsingTriggerCaseNumber(@Param("caseNumber") Long caseNumber );

}