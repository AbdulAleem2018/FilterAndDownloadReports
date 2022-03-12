package com.a2stech.filterandreports.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.a2stech.filterandreports.model.SearchRequest;
import com.a2stech.filterandreports.model.SearchResponce;
import com.a2stech.filterandreports.service.InsurancePlansService;

@RestController
public class InsurancePlansController {
	
	@Autowired
	InsurancePlansService insurancePlansService;
	
	@PostMapping("/plans")
	public ResponseEntity<List<SearchResponce>> getAllInsurancePlans(@RequestBody SearchRequest request){
		List<SearchResponce> planNames= insurancePlansService.filterInsurancePlans(request);
		return new ResponseEntity<>(planNames,HttpStatus.OK);
	}
	
	@GetMapping("/plannames")
	public ResponseEntity<List<String>> getInsurancePlans(){
		List<String> planNames= insurancePlansService.getUniquePlanNames();
		return new ResponseEntity<>(planNames,HttpStatus.OK);
	}
	
	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getInsuranceStatus(){
		List<String> planStatus= insurancePlansService.getUniquePlanStatus();
		return new ResponseEntity<>(planStatus,HttpStatus.OK);
	}
	
	@GetMapping("/download/pdf")
	public void downloadPdfReport(HttpServletResponse responce){
		//DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormatter.format(new Date());
        
		responce.setContentType("application/pdf");
		String headerKey="Content-Disposition";
		String headerValue="attachments; filename=PlanHolders_"+currentDate+".pdf";
		responce.setHeader(headerKey, headerValue);
		
		List<SearchResponce> plansList=insurancePlansService.filterInsurancePlans(null);
		
		insurancePlansService.getPdfReport(plansList,responce);
		//return new ResponseEntity<>(planStatus,HttpStatus.OK);
	}
	
	@GetMapping("/download/excel")
	public  void downloadExcelReport(HttpServletResponse response){
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormatter.format(new Date());
		
		response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=Plans_"+currentDate+".xlsx");
		List<SearchResponce> plansList=insurancePlansService.filterInsurancePlans(null);
		
		 ByteArrayInputStream stream = insurancePlansService.getExcelReport(plansList);
	     try {
			IOUtils.copy(stream, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		insurancePlansService.getExcelReport(plansList);
		//return new ResponseEntity<>(planStatus,HttpStatus.OK);
	}	
}