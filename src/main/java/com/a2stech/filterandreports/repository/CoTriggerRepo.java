package com.a2stech.filterandreports.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.a2stech.filterandreports.entities.CoTriggers;

public interface CoTriggerRepo extends JpaRepository<CoTriggers, Integer>{
	
	@Query("select co from CoTriggers co where co.triggerStatus='PENDING'")
	public List<CoTriggers> getAllPendingTriggers();

}