package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.UserPermissions;

public interface UserPermissionsRepository extends JpaRepository<UserPermissions, String> {
	@Query List<UserPermissions> findByEmail(String email);
}
