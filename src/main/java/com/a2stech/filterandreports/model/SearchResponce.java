/**
 * 
 */
package com.a2stech.filterandreports.model;

import java.time.LocalDate;

import lombok.Data;

/**
 * @author aleem
 * is it required to match entity variable names and SearchRequest and SearchResponce variables?
 */
@Data
public class SearchResponce {
	
	private Integer id;
	private String customerName;
	private String planName;
	private String plansStatus;
	private String ssnNumber;
	private LocalDate createdDate;
	private LocalDate updatedDate;
}
