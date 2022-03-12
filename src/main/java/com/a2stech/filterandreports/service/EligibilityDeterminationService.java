package com.a2stech.filterandreports.service;

import com.a2stech.filterandreports.model.PlanInfo;

public interface EligibilityDeterminationService {

	void saveCitizenPlanDetails(PlanInfo planInfo);

	void saveCitizenTriggerDetails(Long caseNo);

}
