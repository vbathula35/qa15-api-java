package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.Project;
import com.example.demo.model.UserProjectRelationship;

public interface ProjectRepository extends JpaRepository<Project, String>{
	@Transactional
	@Modifying
	@Query ("UPDATE Project SET projectDescription = :desc where projectId = :id")
	void updateStatusById(String id, String desc);
	
	
	@Query("SELECT p FROM Project p JOIN UserProjectRelationship up ON p.projectId = p.projectId WHERE up.email = :email")
	public Page<Project> findProjectsByEmail(String email, Pageable pageable);
	
}


