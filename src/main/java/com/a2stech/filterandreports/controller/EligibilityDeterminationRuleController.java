package com.a2stech.filterandreports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.a2stech.filterandreports.model.EligibilityDeterminationRuleForm;
import com.a2stech.filterandreports.service.EligibilityDeterminationRuleService;

@RestController
public class EligibilityDeterminationRuleController {
	
	@Autowired
	EligibilityDeterminationRuleService edRuleService;

	@PostMapping("/planname")
	public ResponseEntity<String> getInsurancePlanByusingCitizenData(@RequestBody EligibilityDeterminationRuleForm edRuleForm){
		
		String planName=edRuleService.getPlanNameByCitizenData(edRuleForm);
		return new ResponseEntity<String>(planName,HttpStatus.OK);
	}
}
