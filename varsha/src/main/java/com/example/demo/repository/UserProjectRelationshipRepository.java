package com.example.demo.repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Project;
import com.example.demo.model.UserProjectRelationship;

public interface UserProjectRelationshipRepository extends JpaRepository<UserProjectRelationship, Integer>, JpaSpecificationExecutor<UserProjectRelationship> {
	@Transactional
	@Modifying
	@Query("DELETE FROM UserProjectRelationship upr WHERE upr.projectId = :id")
	void deleteByProjectId(Integer id);

	@Transactional
	@Modifying
	@Query("DELETE FROM UserProjectRelationship upr WHERE upr.email = :email AND upr.projectId = :projectId")
	 int deleteByEmailAndProjectId(int projectId, String email);
	
	@Query("SELECT upr FROM UserProjectRelationship upr WHERE upr.email = :email AND upr.projectId = :projectId")
	UserProjectRelationship findByEmailAndProject(String email, int projectId);
	
	
	@Query("SELECT upr FROM UserProjectRelationship upr WHERE upr.projectId = :projectId")
	List<UserProjectRelationship> findAllByProjectId(int projectId);
	
	@Query("SELECT upr FROM UserProjectRelationship upr WHERE upr.id = :id")
	List<UserProjectRelationship> findAllById(int id);
	
	@Query("SELECT upr FROM UserProjectRelationship upr WHERE upr.email = :email")
	List<UserProjectRelationship> findAllByEmail(String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE UserProjectRelationship upr SET upr.isAdmin = :isAdmin, upr.projectStartDate = :projectStartDate, upr.projectEndDate = :projectEndDate, upr.payRate = :payRate, upr.employerPercentage = :employerPercentage WHERE upr.email = :email AND upr.projectId = :projectId")
	int updateByEmailAndProjectId(String email, int projectId, Boolean isAdmin, Date projectStartDate, Date projectEndDate, int payRate, int employerPercentage);
	
}



