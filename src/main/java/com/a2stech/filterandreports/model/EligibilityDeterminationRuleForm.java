package com.a2stech.filterandreports.model;

import lombok.Data;

@Data
public class EligibilityDeterminationRuleForm {
	
	private Long ssn;
	private String userName;
	
	private Double income;
	private Integer kidslowestAge;
	private Integer userAge;
	private boolean employee;
	private String applyToPlanname;
}