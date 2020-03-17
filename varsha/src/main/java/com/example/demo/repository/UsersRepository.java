package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
	@Query("SELECT email FROM UserAuth where email = :email AND password = :password")
	public String loginAuthentication(@Param("email") String email, @Param("password") String password);
}
