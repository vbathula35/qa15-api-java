package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Features;

public interface FeaturesRepository extends JpaRepository<Features, String>{
	
	@Query 
	List<Features> findByvisibleInd(boolean visibleInd);
}
