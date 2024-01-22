package com.example.demo.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Users;


public class UserSpecification {

	
	public static Specification<Users> findAllUsersByFirstName(String firstName){
		return new Specification<Users>() {
			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> firstNamePath = root.get("firstName");
				return cb.like(firstNamePath, "%"+firstName +"%");
			}
		};
	}
	
	
	public static Specification<Users> findAllUsersByLastName(String lastName){
		return new Specification<Users>() {
			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> lastNamePath = root.get("lastName");
				return cb.like(lastNamePath, "%"+lastName+"%");
			}
		};
	}
	

	public static Specification<Users> findAllUsersByEmail(String email){
		return new Specification<Users>() {
			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> emailPath = root.get("email");
				return cb.equal(emailPath, email);
			}
		};
	}
	
	public static Specification<Users> findAllUsersByUserRole(String email){
		return new Specification<Users>() {
			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> userRolePath = root.get("userRole");
				return cb.equal(userRolePath, email);
			}
		};
	}
	
	public static Specification<Users> findAllUsersByUserStatus(String email){
		return new Specification<Users>() {
			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> userStatusPath = root.get("userStatus");
				return cb.equal(userStatusPath, email);
			}
		};
	}
	
	
}
