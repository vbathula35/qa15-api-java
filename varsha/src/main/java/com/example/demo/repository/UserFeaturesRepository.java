package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.UserFeatures;

public interface UserFeaturesRepository extends JpaRepository<UserFeatures, String> {
	@Query List<UserFeatures> findByEmail(String email);
	
	@Transactional
	@Query List<UserFeatures> deleteByEmail(String email);
}
