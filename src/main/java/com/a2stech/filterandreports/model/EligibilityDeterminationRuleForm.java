package com.a2stech.filterandreports.model;

import lombok.Data;

@Data
public class EligibilityDeterminationRuleForm {
	
	private Double income;
	private Integer kidslowestAge;
	private Integer userAge;
	private boolean employee;
	//private String employeementStatus;
}
