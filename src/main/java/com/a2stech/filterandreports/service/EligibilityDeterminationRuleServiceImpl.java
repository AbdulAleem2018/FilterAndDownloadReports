package com.a2stech.filterandreports.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.a2stech.filterandreports.cutomExceptions.InvalidPlanName;
import com.a2stech.filterandreports.entities.InsurancePlans;
import com.a2stech.filterandreports.model.EligibilityDeterminationRuleForm;
import com.a2stech.filterandreports.model.PlanInfo;
import com.a2stech.filterandreports.repository.EligibilityDeterminationRuleRepo;

@Service
public class EligibilityDeterminationRuleServiceImpl implements EligibilityDeterminationRuleService {

	@Autowired
	EligibilityDeterminationRuleRepo edRuleRepo;
	
	String applyPlan=null;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public PlanInfo getPlanInfoByUsingCitizenData(EligibilityDeterminationRuleForm edRuleForm) {

		applyPlan = edRuleForm.getApplyToPlanname().toUpperCase();
		// boolean isEligibleForPlan = false;
		PlanInfo planInfo = new PlanInfo();

		if (applyPlan != null && !applyPlan.equals("")) {
			InsurancePlans insurancePlans = validatePlanName(applyPlan);
			System.out.println(insurancePlans);
			ThreadLocalRandom threadLocalRandom=ThreadLocalRandom.current();
			long case_no=threadLocalRandom.nextLong(910000000001L,910999999999L);
			
			planInfo.setCaseNumber(case_no);
			planInfo.setSsn(edRuleForm.getSsn());
			planInfo.setUserName(edRuleForm.getUserName());

			if (insurancePlans != null && insurancePlans.getPlanName().equals(applyPlan)) {

				planInfo.setPlanId(insurancePlans.getId());
				planInfo.setPlanName(insurancePlans.getPlanName());
				planInfo.setPlanCreatedDate(dateFormat.format(new Date()));
				planInfo.setPlanExpireDate(getPlanExpireDate());

				switch (applyPlan) {

				case "KW":
					if (!edRuleForm.isEmployee()) {
						// isEligibleForPlan = true;
						planInfo.setPlanStatus(insurancePlans.getPlansStatus());
						planInfo.setBenifitAmount(1000);
						planInfo.setDenialReason("Eligible for KW plan");
					} else {
						planInfo.setPlanStatus("DENIED");
						planInfo.setDenialReason("KW plan only for UN-EMPLOYEE's");
					}

					break;
				case "MEDICARE":
					if (edRuleForm.getUserAge() >= 65) {
						// isEligibleForPlan = true;
						planInfo.setPlanStatus(insurancePlans.getPlansStatus());
						planInfo.setBenifitAmount(2000);
						planInfo.setDenialReason("Eligible for MEDICARE plan");
					} else {
						planInfo.setPlanStatus("DENIED");
						planInfo.setDenialReason("MEDICARE plan only for age greter than 65");
					}
					break;
				case "CCAP":
					if (edRuleForm.getIncome() <= 200 & edRuleForm.getKidslowestAge() <= 16) {
						// isEligibleForPlan = true;
						planInfo.setPlanStatus(insurancePlans.getPlansStatus());
						planInfo.setBenifitAmount(3000);
						planInfo.setDenialReason("Eligible for CCAP plan");
					} else {
						planInfo.setPlanStatus("DENIED");
						planInfo.setDenialReason("CCAP plan only for income less than $200 & kids age less than 16");
					}
					break;
				case "SNAP":
					if (edRuleForm.getIncome() <= 200 & edRuleForm.getKidslowestAge() > 16) {
						// isEligibleForPlan = true;
						planInfo.setPlanStatus(insurancePlans.getPlansStatus());
						planInfo.setBenifitAmount(5000);
						planInfo.setDenialReason("Eligible for SNAP plan");
					} else {
						planInfo.setPlanStatus("DENIED");
						planInfo.setDenialReason("SNAP plan only for income less than $200 & kids age greter than 16");
					}
					break;
				case "MEDICAID":
					if (edRuleForm.getIncome() <= 300 && edRuleForm.getIncome() > 200) {
						// isEligibleForPlan = true;
						planInfo.setPlanStatus(insurancePlans.getPlansStatus());
						planInfo.setBenifitAmount(7000);
						planInfo.setDenialReason("Eligible for MEDICAID plan");
					} else {
						planInfo.setPlanStatus("DENIED");
						planInfo.setDenialReason("MEDICAID plan only for income less than $300 & greter than $200");
					}
					break;
				}
			} else {
				throw new InvalidPlanName(applyPlan + " planname not available in ED RULE.");
			}

//			if (isEligibleForPlan) {
//				return planInfo;
//			} else {
//				planInfo.setPlanStatus(applyPlan + " not matched with ED RULE, Please check the ED RULE plans and apply.");
//				//throw new InvalidPlanName(applyPlan + " not matched with ED RULE, Please check the ED RULE plans and apply.");
//			}
			
		} else {
			throw new InvalidPlanName("PlanName is mandatory for validate the plan based on ED RULE");
		}
		return planInfo;
	}
	
	@ExceptionHandler(value=InvalidPlanName.class)
	public ResponseEntity<String> handleInvalidPlanException(){
		return new ResponseEntity<String>(applyPlan+" Plan Name not available in ED RULE.",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private InsurancePlans validatePlanName(String applyToPlanname) {
		return edRuleRepo.findByname(applyToPlanname);
	}

	public String getPlanExpireDate() {
		
		Date date = new Date();
		System.out.println("Plan CreatedDate " + dateFormat.format(date));

		// Convert Date to Calendar
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		// Perform addition/subtraction
		c.add(Calendar.YEAR, 1);
		c.add(Calendar.MONTH, 0);
		c.add(Calendar.DATE, -1);
		// c.add(Calendar.HOUR, -4);
		// c.add(Calendar.MINUTE, 30);
		// c.add(Calendar.SECOND, 50);

		// Convert calendar back to Date
		Date currentDatePlusOneYear = c.getTime();
		String expiredDate=dateFormat.format(currentDatePlusOneYear);
		System.out.println("Expire Date " + expiredDate);

		return expiredDate;
	}
}