package com.a2stech.filterandreports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.a2stech.filterandreports.model.EligibilityDeterminationRuleForm;
import com.a2stech.filterandreports.model.PlanInfo;
import com.a2stech.filterandreports.service.EligibilityDeterminationService;

@RestController
public class EligibilityDeterminationController {
	
	@Autowired
	EligibilityDeterminationService eliDeterService;
	
	@PostMapping("/determine-eligibility")
	public ResponseEntity<String> validatePlanEligibilityDetermination(@RequestBody EligibilityDeterminationRuleForm edRuleForm){
		
		final String URI = "http://localhost:8080/applyplan/"+edRuleForm;

	    RestTemplate restTemplate = new RestTemplate();
	    PlanInfo planInfo = restTemplate.postForObject(URI,edRuleForm,PlanInfo.class);
		
	    eliDeterService.saveCitizenPlanDetails(planInfo);
	    eliDeterService.saveCitizenTriggerDetails(planInfo.getCaseNumber());
		
		return new ResponseEntity<String>("Plan is submitted successfully",HttpStatus.OK);
	}
}
