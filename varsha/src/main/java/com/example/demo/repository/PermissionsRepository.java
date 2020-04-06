package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Permissions;

public interface PermissionsRepository extends JpaRepository<Permissions, String> {
	@Query List<Permissions> findByvisibleInd(boolean visibleInd);
}
