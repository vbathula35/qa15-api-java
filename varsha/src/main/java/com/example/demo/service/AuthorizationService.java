package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.demo.model.Features;
import com.example.demo.model.Permissions;
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
	private RolesRepository rolesRepository;
	private FeaturesRepository featuresRepository;
	private PermissionsRepository permissionsRepository;
	private RoleFeatureRelationshipRepository roleFeatureRelationshipRepository;
	private RoleFeaturePermissionRelationshipRepository roleFeaturePermissionRelationshipRepository;
	
	public AuthorizationService(RolesRepository rolesRepository, 
			FeaturesRepository featuresRepository, 
			UserFeaturesRepository userFeaturesRepository,
			PermissionsRepository permissionsRepository,
			RoleFeatureRelationshipRepository roleFeatureRelationshipRepository,
			RoleFeaturePermissionRelationshipRepository roleFeaturePermissionRelationshipRepository) {
		this.rolesRepository = rolesRepository;
		this.featuresRepository = featuresRepository;
		this.permissionsRepository = permissionsRepository;
		this.roleFeatureRelationshipRepository = roleFeatureRelationshipRepository;
		this.roleFeaturePermissionRelationshipRepository = roleFeaturePermissionRelationshipRepository;
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
			 feat.setDefaultInd(entity.getDefaultInd());
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
			 perm.setDefaultInd(entity.getDefaultInd());
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
			 feat.setDefaultInd(entity.getDefaultInd());
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
			 perm.setDefaultInd(entity.getDefaultInd());
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
			List<Features> featureEntityList = roleFeatureRelationshipRepository.findFeaturesByroleCode(entity.getRoleCode());
			List<Feature> featList = new ArrayList<>();
			for (Features entity1 : featureEntityList) {
				Feature feat = new Feature();
				feat.setFeatureCode(entity1.getFeatureCode());
				feat.setFeatureName(entity1.getFeatureName());
				feat.setFeatureDescription(entity1.getFeatureDescription());
				feat.setVisibleInd(entity1.getVisibleInd());
				feat.setDefaultInd(entity1.getDefaultInd());
				List<Permissions> permissionsEntityList = roleFeaturePermissionRelationshipRepository.findPermissionByfeatueAndroleCode(entity.getRoleCode(), entity1.getFeatureCode());
				List<Permission> permList = new ArrayList<>();
				for (Permissions entity2: permissionsEntityList) {
					Permission perm = new Permission();
					perm.setPermissionCode(entity2.getPermissionCode());
					perm.setPermissionName(entity2.getPermissionName());
					perm.setPermissionDescription(entity2.getPermissionDescription());
					perm.setVisibleInd(entity2.getVisibleInd());
					perm.setDefaultInd(entity2.getDefaultInd());
					permList.add(perm);
				}
				feat.setPermissions(permList);
				featList.add(feat);
			}
			role.setFeatures(featList);
			roleFeatList.add(role);
		}
		return roleFeatList;
	}
}
