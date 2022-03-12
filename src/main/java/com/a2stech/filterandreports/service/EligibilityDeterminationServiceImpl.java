package com.a2stech.filterandreports.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.a2stech.filterandreports.entities.CoTriggers;
import com.a2stech.filterandreports.entities.EligibilityDetails;
import com.a2stech.filterandreports.model.PlanInfo;
import com.a2stech.filterandreports.repository.CoTriggerRepo;
import com.a2stech.filterandreports.repository.EligibilityDeterminationRepo;

@Service
public class EligibilityDeterminationServiceImpl implements EligibilityDeterminationService {
	
	@Autowired
	EligibilityDeterminationRepo eligibilityDeterminationRepo;
	
	@Autowired
	CoTriggerRepo coTriggerRepo;
	
	@Override
	public void saveCitizenPlanDetails(PlanInfo planInfo) {
		EligibilityDetails eligibilityDetails=new EligibilityDetails();
		
		eligibilityDetails.setCaseNo(planInfo.getCaseNumber());
		eligibilityDetails.setBenifitAmount(planInfo.getBenifitAmount());
		eligibilityDetails.setDenialReason(planInfo.getDenialReason());
		eligibilityDetails.setPlanName(planInfo.getPlanName());
		eligibilityDetails.setPlanStatus(planInfo.getPlanStatus());
		eligibilityDetails.setSsn(planInfo.getSsn());
		eligibilityDetails.setPlanCreatedDate(planInfo.getPlanCreatedDate());
		eligibilityDetails.setPlanExpireDate(planInfo.getPlanExpireDate());
		
		//BeanUtils.copyProperties(planInfo, eligibilityDetails); //will not work 
		eligibilityDeterminationRepo.save(eligibilityDetails);
	}

	@Override
	public void saveCitizenTriggerDetails(Long caseNo) {
		CoTriggers trigger=new CoTriggers();
		trigger.setTriggerStatus("PENDING");
		trigger.setCaseNo(caseNo);
		coTriggerRepo.save(trigger);
	}
}