package com.a2stech.filterandreports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.a2stech.filterandreports.entities.CoTriggers;
import com.a2stech.filterandreports.entities.EligibilityDetails;
import com.a2stech.filterandreports.repository.CoTriggerRepo;
import com.a2stech.filterandreports.repository.EligibilityDeterminationRepo;

@Service
public class CorrespondenceServiceImpl implements CorrespondenceService {
	
	@Autowired
	CoTriggerRepo coTriggersRepo;
	
	@Autowired
	EligibilityDeterminationRepo eligibilityDeterminationRepo;
	
	@Override
	public List<CoTriggers> getAllPendingTriggers() {
		return coTriggersRepo.getAllPendingTriggers();
	}

	@Override
	public EligibilityDetails getEligibilityDetailsByTriggerCaseNumber(Long caseNumber) {
		return eligibilityDeterminationRepo.getEligibilityDetailsByUsingTriggerCaseNumber(caseNumber);
	}

	@Override
	public void buildApprovedPlanPDF(EligibilityDetails eligibilityDetails) {
	
	}

	@Override
	public void buildDeniedPlanPDF(EligibilityDetails eligibilityDetails) {

	}
}
