package com.a2stech.filterandreports.service;

import com.a2stech.filterandreports.model.EligibilityDeterminationRuleForm;
import com.a2stech.filterandreports.model.PlanInfo;

public interface EligibilityDeterminationRuleService {

	PlanInfo getPlanInfoByUsingCitizenData(EligibilityDeterminationRuleForm edRuleForm);

}
