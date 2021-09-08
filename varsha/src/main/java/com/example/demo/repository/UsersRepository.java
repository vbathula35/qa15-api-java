package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Users;

public interface UsersRepository extends JpaRepository<Users, String>, JpaSpecificationExecutor<Users> {
	
	@Query List<Users> findByEmail(String email);
	@Query List<Users> findByFirstName(String firstName);
	@Query List<Users> findByLastName(String lastName);
	@Query List<Users> findByAddressLine1(String addressLine1);
	@Query List<Users> findByCity(String city);
	@Query List<Users> findByState(String state);
	@Query List<Users> findByCountry(String country);
	@Query List<Users> findByZipCode(Integer zipCode);
	@Query List<Users> findByUserRole(String userRole);
}
