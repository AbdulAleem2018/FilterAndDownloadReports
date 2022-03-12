package com.a2stech.filterandreports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a2stech.filterandreports.entities.CoTriggers;
import com.a2stech.filterandreports.entities.EligibilityDetails;
import com.a2stech.filterandreports.service.CorrespondenceService;

@RestController
public class CorrespondenceController {
	
	@Autowired
	CorrespondenceService correspondenceService;
	
	@GetMapping
	public List<CoTriggers> getAllPendingTriggers(){
		
		List<CoTriggers> pendingTriggers=correspondenceService.getAllPendingTriggers();
		
		for(CoTriggers coTriggers:pendingTriggers) {
			EligibilityDetails eligibilityDetails= correspondenceService.getEligibilityDetailsByTriggerCaseNumber(coTriggers.getCaseNo());
			
			if(eligibilityDetails.getPlanStatus().equalsIgnoreCase("APPROVED")) {
				correspondenceService.buildApprovedPlanPDF(eligibilityDetails);
			} else {
				correspondenceService.buildDeniedPlanPDF(eligibilityDetails);
			}
		}
		return pendingTriggers;
	}	
}