package com.a2stech.filterandreports.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.a2stech.filterandreports.model.SearchRequest;
import com.a2stech.filterandreports.model.SearchResponce;

public interface InsurancePlansService {
	
	List<String> getUniquePlanNames();
	List<String> getUniquePlanStatus();
	List<SearchResponce> filterInsurancePlans(SearchRequest request);
	void getPdfReport(List<SearchResponce> planlist,HttpServletResponse responce);
	ByteArrayInputStream getExcelReport(List<SearchResponce> planlist);
}
