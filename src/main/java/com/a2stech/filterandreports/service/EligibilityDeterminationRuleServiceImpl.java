package com.a2stech.filterandreports.service;

import org.springframework.stereotype.Service;
import com.a2stech.filterandreports.model.EligibilityDeterminationRuleForm;

@Service
public class EligibilityDeterminationRuleServiceImpl implements EligibilityDeterminationRuleService {

	@Override
	public String getPlanNameByCitizenData(EligibilityDeterminationRuleForm edRuleForm) {
		//System.out.println("Employee ? "+edRuleForm.getEmployeementStatus());
		System.out.println("is Employee ? "+edRuleForm.isEmployee());
		System.out.println("Income :"+edRuleForm.getIncome());
		System.out.println("Kids Age :"+edRuleForm.getKidslowestAge());
		System.out.println("Citizen Age :"+edRuleForm.getUserAge());
		
		if (!edRuleForm.isEmployee())
		//if (edRuleForm.getEmployeementStatus().equalsIgnoreCase("Un-employee"))
			return "KW";
		else if (edRuleForm.getUserAge() >= 65)
			return "Medicare";
		else if (edRuleForm.getIncome() <= 200 & edRuleForm.getKidslowestAge() <= 16)
			return "CCAP";
		else if(edRuleForm.getIncome() <= 200 & edRuleForm.getKidslowestAge()> 16)
			return "SNAP";
		else if (edRuleForm.getIncome() <= 300 && edRuleForm.getIncome()>200)
			return "Medicaid";
		else
			return "No Plan";

	}
}