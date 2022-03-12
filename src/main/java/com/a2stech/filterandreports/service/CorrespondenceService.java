package com.a2stech.filterandreports.service;

import java.util.List;
import com.a2stech.filterandreports.entities.CoTriggers;
import com.a2stech.filterandreports.entities.EligibilityDetails;

public interface CorrespondenceService {
	
	public List<CoTriggers> getAllPendingTriggers();

	public EligibilityDetails getEligibilityDetailsByTriggerCaseNumber(Long caseNumber);

	public void buildApprovedPlanPDF(EligibilityDetails eligibilityDetails);

	public void buildDeniedPlanPDF(EligibilityDetails eligibilityDetails);
	
}