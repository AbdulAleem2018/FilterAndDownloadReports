package com.a2stech.filterandreports.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.a2stech.filterandreports.entities.InsurancePlans;

public interface InsurancePlansRepo extends JpaRepository<InsurancePlans, Integer>{
	
	@Query("select distinct planName from InsurancePlans")
	List<String> getUniquePlanNames();
	
	@Query("select distinct plansStatus from InsurancePlans")
	List<String> getUniquePlanStatus();
}