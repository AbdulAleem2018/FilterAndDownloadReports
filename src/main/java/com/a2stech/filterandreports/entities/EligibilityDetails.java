package com.a2stech.filterandreports.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ELIGIBILITY_DETAILS")
public class EligibilityDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ELIGIBLE_ID")
	private Integer eligibleId;
	
	@Column(name="CASE_NO")
	private Long caseNo;
	
	@Column(name="SSN")
	private Long ssn;
	
	@Column(name="DENIAL_REASON")
	private String denialReason;
	
	@Column(name="BENIFIT_AMOUNT")
	private double benifitAmount;
	
	@Column(name="PLAN_NAME")
	private String planName;
	
	@Column(name="PLAN_STATUS")
	private String planStatus;
	
	@Column(name="PLAN_START_DATE")
	private String planCreatedDate;
	
	@Column(name="PLAN_END_DATE")
	private String planExpireDate;	
}
