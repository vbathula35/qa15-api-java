package com.example.demo.repository;
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

	List<UserProjectRelationship> findAllById(int id);
	
	List<UserProjectRelationship> findAllByProjectId(int projectId);
	
	List<UserProjectRelationship> findAllByEmail(String email);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM UserProjectRelationship upr WHERE upr.email = :email AND upr.projectId = :projectId")
	 void deleteByEmailAndProjectId(int projectId, String email);
	
}



