package com.example.demo.repository;
import java.time.LocalDate;
import java.util.Date;

import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.Project;
import com.example.demo.model.UserEvent;

public interface ProjectRepository extends JpaRepository<Project, Integer> , JpaSpecificationExecutor<Project>{
	@Transactional
	@Modifying
	@Query ("UPDATE Project SET projectDescription = :desc where id = :id")
	void updateStatusById(Integer id, String desc);
	
	
	@Query("SELECT p FROM Project p JOIN UserProjectRelationship up ON p.id = up.projectId WHERE up.email = :email")
	public Page<Project> findProjectsByEmail(String email, Pageable pageable);
	
	@Transactional
	@Modifying
	@Query ("UPDATE Project SET projectName = :projectName, projectLocation = :projectLocation, projectDescription = :projectDescription, modifiedDate = :modifiedDate where id = :id")
	int updateProjectById(String projectName, String projectLocation, String projectDescription, LocalDate modifiedDate, int id);
	
	
}