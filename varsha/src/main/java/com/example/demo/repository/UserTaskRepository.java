package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.UserTask;

public interface UserTaskRepository extends JpaRepository<UserTask, Integer>, JpaSpecificationExecutor<UserTask> {
	@Query UserTask findByTaskName(String taskName);

	@Transactional
	@Modifying
	@Query ("UPDATE UserTask SET status = :status where id = :id")
	void updateStatusById(Integer id, String status);
	

	
}
