package com.a2stech.filterandreports.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import lombok.Data;

@Data
@Entity
@Table(name="USER_REGISTRATIONS")
public class UserRegistrations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
//	@OneToOne
//	@JoinColumn
//	ApplicationRegistration applicationRegistration;
	
	@Column(name="USER_REGISTRATION_NO")
	private String userRegistrationNo;
	
	@ForeignKey(name = "SSN")
	@Column(name="SSN_FK")
	private Long ssn;
	
	@ForeignKey(name = "MOBILE_NO")
	@Column(name="MOBILE_NO_FK")
	private Long mobileNo;
	
	@ForeignKey(name = "DATE_OF_BIRTH")
	@Column(name="DATE_OF_BIRTH_FK")
	private LocalDate dateOfBirth;

}
