package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
	@Query List<Users> findByFirstName(String firstName);
	@Query List<Users> findByLastName(String lastName);
	@Query List<Users> findByAddressLine1(String addressLine1);
	@Query List<Users> findByCity(String city);
	@Query List<Users> findByState(String state);
	@Query List<Users> findByCountry(String country);
	@Query List<Users> findByZipCode(Integer zipCode);
	@Query List<Users> findByUserRole(String userRole);
	
//	@Query("UPDATE email, registerDate, userStatus FROM UserAuth where email = :email AND password = :password")
//	public Object loginAuthentication(@Param("email") String email, @Param("password") String password);
	
//	@Modifying
//    @Query("UPDATE Users c SET c.address = :address WHERE c.id = :companyId")
//    int updateProfle(@Param("companyId") int companyId, @Param("email") String address);

}
