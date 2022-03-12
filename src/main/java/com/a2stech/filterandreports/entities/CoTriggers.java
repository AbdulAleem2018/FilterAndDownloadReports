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
@Table(name="CO_TRIGGERS")
public class CoTriggers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TRIGGER_ID")
	private Integer triggerId;
	
	@Column(name = "CASE_NO")
	private Long caseNo;
	
	@Column(name = "TRIGGER_STATUS")
	private String triggerStatus;
}
