package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.UserPermissions;

public interface UserPermissionsRepository extends JpaRepository<UserPermissions, String> {
	@Query List<UserPermissions> findByEmail(String email);
	
	@Transactional
	@Query List<UserPermissions> deleteByEmail(String email);
}
