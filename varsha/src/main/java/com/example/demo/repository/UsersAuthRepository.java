package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.UserAuth;

public interface UsersAuthRepository extends JpaRepository<UserAuth, String> {
	@Query List<UserAuth> findByRegisterDate(Date registerDate);
	@Query List<UserAuth> findByUserStatus(String userStatus);
	
//	@Query("SELECT email, registerDate, userStatus FROM UserAuth where email = :email AND password = :password")
//	public Object loginAuthentication(@Param("email") String email, @Param("password") String password);
	
}