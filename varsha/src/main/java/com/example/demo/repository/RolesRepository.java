package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, String>{
	@Query List<Roles> findByvisibleInd(boolean visibleInd);
}
