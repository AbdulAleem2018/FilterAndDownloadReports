package com.a2stech.filterandreports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.a2stech.filterandreports.entities.InsurancePlans;

public interface EligibilityDeterminationRuleRepo extends JpaRepository<InsurancePlans, Integer> {

	@Query("select p from InsurancePlans p where p.plansStatus='APPROVED' and p.planName = :#{#planname}")
	InsurancePlans findByname(@Param("planname") String planname);
}
