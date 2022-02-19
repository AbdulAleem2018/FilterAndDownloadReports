package com.a2stech.filterandreports.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
/*
Hibernate: drop table if exists insurance_plans CASCADE 
Hibernate: drop sequence if exists hibernate_sequence
Hibernate: create sequence hibernate_sequence start with 1 increment by 1
Hibernate: create table insurance_plans (id integer not null, created_date date, customer_name varchar(255), plan_name varchar(255), plan_status varchar(255), ssn_no varchar(255), updated_date date, primary key (id))

INSERT INTO INSURANCE_PLANS VALUES (1, 'Aleem', 'Jivan Anand', 'APPROVED', '774868534264', 08-12-2021, 08-12-2021) [22007-200]
 */

@Data
@Entity
@Table(name="INSURANCE_PLANS")
public class InsurancePlans {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	
	@Column(name="PLAN_NAME")
	private String planName;
	
	@Column(name="PLAN_STATUS")
	private String plansStatus;	
	
	@Column(name="SSN_NO")
	private String ssnNumber;
	
	@Column(name="CREATED_DATE")
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name="UPDATED_DATE")
	@UpdateTimestamp
	private LocalDate updatedDate;
}