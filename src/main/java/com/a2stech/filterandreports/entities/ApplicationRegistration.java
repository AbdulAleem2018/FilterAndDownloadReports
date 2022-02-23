package com.a2stech.filterandreports.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

@Data
@Entity
@Table(name="APPLICATION_REGISTRATION")
public class ApplicationRegistration implements Serializable {

	@EmbeddedId
	private ApplicationRegistrationPK appRegPk;
	
//	@OneToOne
//	@JoinColumn
//	UserRegistrations userRegistrations;
	
	@Column(name="FULL_NAME")
	private String fullName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="REGISTRATION_NO")
	private String registrationNo;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="CREATED_DATE")
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name="UPDATED_DATE")
	@UpdateTimestamp
	private LocalDate updatedDate;
}
