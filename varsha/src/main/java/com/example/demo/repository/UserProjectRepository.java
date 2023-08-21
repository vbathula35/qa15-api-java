package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.UserProject;

public interface UserProjectRepository extends JpaRepository<UserProject, Integer>, JpaSpecificationExecutor<UserProject> {
	@Transactional
	@Modifying
	@Query ("UPDATE UserProject SET status = :desc where id = :id")
	void updateUserProjectById(String id, String desc);
}


