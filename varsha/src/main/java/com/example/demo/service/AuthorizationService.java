package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Features;
import com.example.demo.model.Permissions;
import com.example.demo.model.RoleFeaturePermissionRelationship;
import com.example.demo.model.RoleFeatureRelationship;
import com.example.demo.model.Roles;
import com.example.demo.object.Feature;
import com.example.demo.object.Permission;
import com.example.demo.object.Role;
import com.example.demo.repository.FeaturesRepository;
import com.example.demo.repository.PermissionsRepository;
import com.example.demo.repository.RoleFeaturePermissionRelationshipRepository;
import com.example.demo.repository.RoleFeatureRelationshipRepository;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UserFeaturesRepository;

@Service
public class AuthorizationService {
	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	private FeaturesRepository featuresRepository;
	@Autowired
	private PermissionsRepository permissionsRepository;
	@Autowired
	private RoleFeatureRelationshipRepository roleFeatureRelationshipRepository;
	@Autowired
	private RoleFeaturePermissionRelationshipRepository roleFeaturePermissionRelationshipRepository;

	
	
	public List<String> getRoleFeaturesStringArr(String role) {
		List<RoleFeatureRelationship> roleFeaturesList = roleFeatureRelationshipRepository.findByRoleCode(role);
		List<String> featStrinArr = roleFeaturesList.stream().map(f -> f.getFeatureCode()).collect(Collectors.toList());
		return featStrinArr;
	}
	
	public List<String> getRolePermissionsStringArr(String role) {
		List<RoleFeaturePermissionRelationship> rolePermissionList = roleFeaturePermissionRelationshipRepository.findByRoleCode(role);
		List<String> permStrinArr = rolePermissionList.stream().map(f -> f.getPermissionCode()).collect(Collectors.toList());
		return permStrinArr;
	}
	
	public List<Roles> getAllRoles() throws InterruptedException, ExecutionException {
		 List<Roles> roleEntityList = rolesRepository.findAll();
		 List<Roles> roList = new ArrayList<>();
		 for (Roles entity : roleEntityList) {
			 Roles role = new Roles();
			 role.setRoleCode(entity.getRoleCode());
			 role.setRoleName(entity.getRoleName());
			 role.setRoleDescription(entity.getRoleDescription());
			 role.setVisibleInd(entity.getVisibleInd());
			 roList.add(role);
		}	 
		return roList;
	}
	
	public List<Features> getAllFeatures() throws InterruptedException, ExecutionException {
		 List<Features> featureEntityList = featuresRepository.findAll();
		 List<Features> featList = new ArrayList<>();
		 for (Features entity : featureEntityList) {
			 Features feat = new Features();
			 feat.setFeatureCode(entity.getFeatureCode());
			 feat.setFeatureName(entity.getFeatureName());
			 feat.setFeatureDescription(entity.getFeatureDescription());
			 feat.setVisibleInd(entity.getVisibleInd());
			 featList.add(feat);
		}	 
		return featList;
	}
	
	public List<Permissions> getAllPermissions() throws InterruptedException, ExecutionException {
		 List<Permissions> permissionEntityList = permissionsRepository.findAll();
		 List<Permissions> permList = new ArrayList<>();
		 for (Permissions entity : permissionEntityList) {
			 Permissions perm = new Permissions();
			 perm.setPermissionCode(entity.getPermissionCode());
			 perm.setPermissionName(entity.getPermissionName());
			 perm.setPermissionDescription(entity.getPermissionDescription());
			 perm.setVisibleInd(entity.getVisibleInd());
			 permList.add(perm);
		}	 
		return permList;
	}
	
	public List<Roles> getActiveRoles() throws InterruptedException, ExecutionException {
		 List<Roles> roleEntityList = rolesRepository.findByvisibleInd(true);
		 List<Roles> roList = new ArrayList<>();
		 for (Roles entity : roleEntityList) {
			 Roles role = new Roles();
			 role.setRoleCode(entity.getRoleCode());
			 role.setRoleName(entity.getRoleName());
			 role.setRoleDescription(entity.getRoleDescription());
			 role.setVisibleInd(entity.getVisibleInd());
			 roList.add(role);
		}	 
		return roList;
	}
	
	public List<Features> getActiveFeatures() throws InterruptedException, ExecutionException {
		 List<Features> featureEntityList = featuresRepository.findByvisibleInd(false);
		 List<Features> featList = new ArrayList<>();
		 for (Features entity : featureEntityList) {
			 Features feat = new Features();
			 feat.setFeatureCode(entity.getFeatureCode());
			 feat.setFeatureName(entity.getFeatureName());
			 feat.setFeatureDescription(entity.getFeatureDescription());
			 feat.setVisibleInd(entity.getVisibleInd());
			 featList.add(feat);
		}	 
		return featList;
	}
	
	public List<Permissions> getActivePermissions() throws InterruptedException, ExecutionException {
		 List<Permissions> permissionEntityList = permissionsRepository.findByvisibleInd(true);
		 List<Permissions> permList = new ArrayList<>();
		 for (Permissions entity : permissionEntityList) {
			 Permissions perm = new Permissions();
			 perm.setPermissionCode(entity.getPermissionCode());
			 perm.setPermissionName(entity.getPermissionName());
			 perm.setPermissionDescription(entity.getPermissionDescription());
			 perm.setVisibleInd(entity.getVisibleInd());
			 permList.add(perm);
		}	 
		return permList;
	}
	
	
	
	public List<Role> getAllRolesFeaturesPermissions() throws InterruptedException, ExecutionException {
		List<Roles> roleEntityList = getAllRoles();
		List<Role> roleFeatList = new ArrayList<>();
		for (Roles entity : roleEntityList) {
			Role role = new Role();
			role.setRoleCode(entity.getRoleCode());
			role.setRoleName(entity.getRoleName());
			role.setRoleDescription(entity.getRoleDescription());
			role.setVisibleInd(entity.getVisibleInd());
			List<RoleFeatureRelationship> featureEntityList = roleFeatureRelationshipRepository.findByRoleCode(entity.getRoleCode());
			List<Feature> featList = new ArrayList<>();
			for (RoleFeatureRelationship entity1 : featureEntityList) {
				Feature feature = new Feature();
				Features feat = featuresRepository.findById(entity1.getFeatureCode()).orElse(new Features());
				feature.setFeatureCode(feat.getFeatureCode());
				feature.setFeatureName(feat.getFeatureName());
				feature.setFeatureDescription(feat.getFeatureDescription());
				feature.setVisibleInd(entity.getVisibleInd() && feat.getVisibleInd() && entity1.isVisibleInd());
				List<RoleFeaturePermissionRelationship> permissionsEntityList = roleFeaturePermissionRelationshipRepository.findPermissionByRoleAndFeature(entity.getRoleCode(), entity1.getFeatureCode());
				List<Permission> permList = new ArrayList<>();
				for (RoleFeaturePermissionRelationship entity2: permissionsEntityList) {
					Permission permission = new Permission();
					Permissions perm = permissionsRepository.findById(entity2.getPermissionCode()).orElse(new Permissions());
					permission.setPermissionCode(perm.getPermissionCode());
					permission.setPermissionName(perm.getPermissionName());
					permission.setPermissionDescription(perm.getPermissionDescription());
					permission.setVisibleInd(perm.getVisibleInd() && entity1.isVisibleInd() && entity2.getVisibleInd() && entity.getVisibleInd());
					permList.add(permission);
				}
				feature.setPermissions(permList);
				featList.add(feature);
			}
			role.setFeatures(featList);
			roleFeatList.add(role);
		}
		return roleFeatList;
	}
	
	public Role getRoleAndFeatureAndPermission(String RoleCode) throws InterruptedException, ExecutionException {
		Role role = new Role();
		Roles roleEntityList = rolesRepository.findById(RoleCode).orElse((new Roles()));
		role.setRoleCode(roleEntityList.getRoleCode());
		role.setRoleName(roleEntityList.getRoleName());
		role.setRoleDescription(roleEntityList.getRoleDescription());
		role.setVisibleInd(roleEntityList.getVisibleInd());
		
		List<RoleFeatureRelationship> featureEntityList = roleFeatureRelationshipRepository.findByRoleCode(role.getRoleCode());
		List<Feature> featList = new ArrayList<>();
		for (RoleFeatureRelationship entity1 : featureEntityList) {
			Feature feature = new Feature();
			Features feat = featuresRepository.findById(entity1.getFeatureCode()).orElse(new Features());
			feature.setFeatureCode(feat.getFeatureCode());
			feature.setFeatureName(feat.getFeatureName());
			feature.setFeatureDescription(feat.getFeatureDescription());
			feature.setVisibleInd(roleEntityList.getVisibleInd() && feat.getVisibleInd() && entity1.isVisibleInd());
			List<RoleFeaturePermissionRelationship> permissionsEntityList = roleFeaturePermissionRelationshipRepository.findPermissionByRoleAndFeature(role.getRoleCode(), entity1.getFeatureCode());
			List<Permission> permList = new ArrayList<>();
			for (RoleFeaturePermissionRelationship entity2: permissionsEntityList) {
				Permission permission = new Permission();
				Permissions perm = permissionsRepository.findById(entity2.getPermissionCode()).orElse(new Permissions());
				permission.setPermissionCode(perm.getPermissionCode());
				permission.setPermissionName(perm.getPermissionName());
				permission.setPermissionDescription(perm.getPermissionDescription());
				permission.setVisibleInd(perm.getVisibleInd() && entity1.isVisibleInd() && entity2.getVisibleInd() && roleEntityList.getVisibleInd());
				permList.add(permission);
			}
			feature.setPermissions(permList);
			featList.add(feature);
		}
		role.setFeatures(featList);
		return role;
	}
}
