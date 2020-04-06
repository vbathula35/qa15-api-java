package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Features;
import com.example.demo.model.RoleFeatureRelationship;

public interface RoleFeatureRelationshipRepository extends JpaRepository<RoleFeatureRelationship, String> {
	@Query List<RoleFeatureRelationship> findByFeatureCode(String featureCode);
	@Query List<RoleFeatureRelationship> findByRoleCode(String roleCode);
	@Query("SELECT f FROM RoleFeatureRelationship rf INNER JOIN Features f ON rf.featureCode = f.featureCode WHERE rf.roleCode = :roleCode")
	public List<Features> findFeaturesByroleCode(String roleCode);
}
