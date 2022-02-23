package com.a2stech.filterandreports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.a2stech.filterandreports.entities.ApplicationRegistration;
import com.a2stech.filterandreports.model.ApplicationRegistrationForm;
import com.a2stech.filterandreports.service.ApplicationRegistrationService;

@RestController
public class ApplicationRegistrationController {

	@Autowired
	ApplicationRegistrationService appRegService;
	
	@PostMapping("/saveuser")
	public ResponseEntity<String> createUser(@RequestBody ApplicationRegistrationForm appForm){
		String registrationMessage="";
		
		if(appForm.getSsn()!=null) {
			String SSA_WEB_API_ENDPOINT="https://ssa-web-api.herokuapp.com/state/"+appForm.getSsn();
			
			RestTemplate restTemplate=new RestTemplate();
		    String citizenState=restTemplate.getForObject(SSA_WEB_API_ENDPOINT,String.class);
		   // String citizenState=responceEntity.getBody();
		    System.out.println("User belongs to :"+citizenState+" State");
		    
		    if(citizenState.equals("Kentucky") ){
		    	registrationMessage=appRegService.saveCitizenDetails(appForm);
		    } else {
		    	registrationMessage="Invalide SSN Number";
		    }
		}
		return new ResponseEntity<String>(registrationMessage,HttpStatus.OK);
	}
	
	@GetMapping("/allusers")
	public ResponseEntity<List<ApplicationRegistration>> getAllUsers(){
		List<ApplicationRegistration> usersList=(List<ApplicationRegistration>) appRegService.getAllKESCitizenDetails();
		return new ResponseEntity<List<ApplicationRegistration>>(usersList,HttpStatus.OK);
	}
}