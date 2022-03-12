package com.a2stech.filterandreports.model;

import lombok.Data;

@Data
public class PlanInfo {
	
	private Long ssn;
	private String userName;
	private double benifitAmount;
	private Integer planId;
	private String planName;
	private String planStatus;
	private Long caseNumber;
	private String denialReason;
	private String planCreatedDate;
	private String planExpireDate;
}
