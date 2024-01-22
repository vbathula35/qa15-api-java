package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Permissions;
import com.example.demo.model.RoleFeaturePermissionRelationship;

public interface RoleFeaturePermissionRelationshipRepository extends JpaRepository<RoleFeaturePermissionRelationship, String>{
	@Query List<RoleFeaturePermissionRelationship> findByRoleCode(String roleCode);
	@Query List<RoleFeaturePermissionRelationship> findByFeatureCode(String featureCode);
	@Query List<RoleFeaturePermissionRelationship> findByPermissionCode(String permissionCode);
	
	@Query ("SELECT rfp FROM RoleFeaturePermissionRelationship rfp WHERE rfp.roleCode = :roleCode AND rfp.featureCode = :featureCode")
	public List<RoleFeaturePermissionRelationship> findPermissionByRoleAndFeature(String roleCode, String featureCode);
	
//	@Query("SELECT p FROM RoleFeaturePermissionRelationship rfp JOIN Permissions p ON rfp.permissionCode = p.permissionCode WHERE rfp.roleCode = :roleCode AND rfp.featureCode = :featureCode")
//	public List<RoleFeaturePermissionRelationship> findPermissionByfeatueAndroleCode(String roleCode, String featureCode);
}
