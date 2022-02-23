package com.a2stech.filterandreports.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Embeddable;
import org.springframework.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRegistrationPK implements Serializable{
	private static final long serialVersionUID = 1L;

	@NonNull
	private Long ssn;
	@NonNull
	private Long mobileNo;
	@NonNull
	private LocalDate dateOfBirth;
}
