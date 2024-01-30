package com.example.demo.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.RoleFeaturePermissionRelationship;
import com.example.demo.model.UserTimesheets;
import com.example.demo.model.Users;

public class UserTimesheetSpecification {

	
	
	public static Specification<UserTimesheets> findAllTimesheetsByEmail(String email){
		return new Specification<UserTimesheets>() {
			@Override
			public Predicate toPredicate(Root<UserTimesheets> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> emailPath = root.get("email");
				Path<String> projectIdPath = root.get("projectId");
				return cb.like(emailPath, "%"+email +"%");
			}
		};
	}
	
	
	public static Specification<UserTimesheets> findAllTimesheetsByProjectId(int projectId){
		return new Specification<UserTimesheets>() {
			@Override
			public Predicate toPredicate(Root<UserTimesheets> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> projectIdPath = root.get("projectId");
				return cb.like(projectIdPath, "%"+projectId+"%");
			}
		};
	}
	

	public static Specification<UserTimesheets> findAllTimesheetsByMonth(int month){
		return new Specification<UserTimesheets>() {
			@Override
			public Predicate toPredicate(Root<UserTimesheets> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> monthPath = root.get("month");
				return cb.equal(monthPath, month);
			}
		};
	}
	
	public static Specification<UserTimesheets> findAllUsersByUserRole(String year){
		return new Specification<UserTimesheets>() {
			@Override
			public Predicate toPredicate(Root<UserTimesheets> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> yearPath = root.get("year");
				return cb.equal(yearPath, year);
			}
		};
	}
	
	
}




