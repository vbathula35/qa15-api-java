package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.UserSubscriptions;
import com.example.demo.model.UserTask;

public interface UserSubscriptionsRepository extends JpaRepository<UserSubscriptions, Integer>, JpaSpecificationExecutor<UserSubscriptions> {
	@Query UserTask findByEmail(String email);
	
	@Transactional
	@Modifying
	@Query ("UPDATE UserSubscriptions SET status = :status where id = :id")
	void updateStatusById(Integer id, String status);
}
