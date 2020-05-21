package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.UserEvent;
import com.example.demo.model.UserTask;


public interface UserEventRepository extends JpaRepository<UserEvent, Integer>, JpaSpecificationExecutor<UserEvent>{
	@Query UserEvent findByEventName(String eventName);
	
	@Transactional
	@Modifying
	@Query ("UPDATE UserEvent SET status = :status where id = :id")
	void updateStatusById(Integer id, String status);
}
