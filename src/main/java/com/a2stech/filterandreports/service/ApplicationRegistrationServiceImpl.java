package com.a2stech.filterandreports.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.a2stech.filterandreports.entities.ApplicationRegistration;
import com.a2stech.filterandreports.entities.ApplicationRegistrationPK;
import com.a2stech.filterandreports.entities.UserRegistrations;
import com.a2stech.filterandreports.model.ApplicationRegistrationForm;
import com.a2stech.filterandreports.repository.ApplicationRegistrationRepo;

@Service
public class ApplicationRegistrationServiceImpl implements ApplicationRegistrationService{
	
	@Autowired
	ApplicationRegistrationRepo appRegRepo;

	@Override
	public String saveCitizenDetails(ApplicationRegistrationForm appRegForm) {
		
		ApplicationRegistration appReg_entity=new ApplicationRegistration();
		ApplicationRegistrationPK AppRegPK=new ApplicationRegistrationPK(appRegForm.getSsn(), appRegForm.getMobileNo(),appRegForm.getDateOfBirth());
		//AppRegPK.setMobileNo(null);
		//AppRegPK.setSsn(null);
		appReg_entity.setAppRegPk(AppRegPK);
		
//		UserRegistrations userRegistrations=new UserRegistrations();
//		userRegistrations.setUserRegistrationNo(getRegistrationNo());
//		userRegistrations.setDateOfBirth(appRegForm.getDateOfBirth());
//		userRegistrations.setMobileNo(appRegForm.getSsn());
//		userRegistrations.setSsn(appRegForm.getSsn());
//		appReg_entity.setUserRegistrations(userRegistrations);
		
		String userRegistrationNo=getRegistrationNo();
		appReg_entity.setRegistrationNo(userRegistrationNo);
		
		BeanUtils.copyProperties(appRegForm, appReg_entity);
		
		appReg_entity=appRegRepo.save(appReg_entity);
		
	//	String userRegistrationNo=appReg_entity.getUserRegistrations().getUserRegistrationNo();
		
		if(userRegistrationNo!=null && !userRegistrationNo.equals(""))
			return "Application Registration is successfully completed for "+appReg_entity.getFullName()+" and Registration No is "+userRegistrationNo;
		else
			return "Citizen not belongs to Kentuky state, we can't provide Health Insurance for this Citizen...";		
	}

	private String getRegistrationNo() {
		// Generate random integers in range 0 to 1000000
        int rand_int1 = ThreadLocalRandom.current().nextInt(1000000);
		return "AR"+rand_int1;
	}

	@Override
	public List<ApplicationRegistration> getAllKESCitizenDetails() {
		return appRegRepo.findAll();
	}	
}