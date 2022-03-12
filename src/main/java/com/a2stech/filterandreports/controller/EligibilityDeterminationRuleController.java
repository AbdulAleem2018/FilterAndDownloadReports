package com.a2stech.filterandreports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.a2stech.filterandreports.model.EligibilityDeterminationRuleForm;
import com.a2stech.filterandreports.model.PlanInfo;
import com.a2stech.filterandreports.service.EligibilityDeterminationRuleService;

@RestController
public class EligibilityDeterminationRuleController {
	
	@Autowired
	EligibilityDeterminationRuleService edRuleService;

	@PostMapping("/applyplan/{plandata}")
	public ResponseEntity<PlanInfo> getInsurancePlanByusingCitizenData(@RequestBody EligibilityDeterminationRuleForm plandata){
		
		PlanInfo planInfo=edRuleService.getPlanInfoByUsingCitizenData(plandata);
		return new ResponseEntity<PlanInfo>(planInfo,HttpStatus.OK);
	}
}
