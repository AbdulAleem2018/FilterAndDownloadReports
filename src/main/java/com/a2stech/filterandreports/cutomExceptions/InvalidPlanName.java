package com.a2stech.filterandreports.cutomExceptions;

public class InvalidPlanName extends RuntimeException{
	
	private static final long serialVersionUID = 389604175892257953L;

	public InvalidPlanName(String message) {
		super(message);
	}
}