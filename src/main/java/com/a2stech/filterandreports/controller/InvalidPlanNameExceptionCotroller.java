package com.a2stech.filterandreports.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.a2stech.filterandreports.cutomExceptions.InvalidPlanName;

@RestControllerAdvice
public class InvalidPlanNameExceptionCotroller {
	
//	@ExceptionHandler(value=InvalidPlanName.class)
//	public ResponseEntity<?> handleInvalidPlanException(){
//		return new ResponseEntity<>("Global Plan Name not available in ED RULE.",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
//	@ExceptionHandler(value=InvalidPlanName.class)
//	public ResponseEntity<?> handleMandatoryPlanException(){
//		return new ResponseEntity<>("Global Plan Mandatory in ED RULE.",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}