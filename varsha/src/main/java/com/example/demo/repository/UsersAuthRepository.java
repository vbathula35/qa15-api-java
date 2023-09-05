package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.UserAuth;

public interface UsersAuthRepository extends JpaRepository<UserAuth, String> {
	@Query List<UserAuth> findByRegisterDate(Date registerDate);
	@Query List<UserAuth> findByUserStatus(String userStatus);
	
	@Transactional
	@Modifying
	@Query ("UPDATE UserAuth SET userStatus = :status where email = :email")
	void saveUserStatusByEmail(String email, String status);
	
//	@Query("SELECT email, registerDate, userStatus FROM UserAuth where email = :email AND password = :password")
//	public Object loginAuthentication(@Param("email") String email, @Param("password") String password);
	
}