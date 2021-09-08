package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.FeaturePermissionRelationship;
import com.example.demo.model.Permissions;

public interface FeaturePermissionRelationshipRepository extends JpaRepository<FeaturePermissionRelationship, String> {
	
	@Query List<FeaturePermissionRelationship> findByFeatureCode(String featureCode);
	@Query List<FeaturePermissionRelationship> findByPermissionCode(String permissionCode);
	@Query("SELECT p FROM FeaturePermissionRelationship fp INNER JOIN Permissions p ON fp.permissionCode = p.permissionCode WHERE fp.featureCode = :featureCode")
	public List<Permissions> findPermissionsByfeatureCode(String featureCode);
}
