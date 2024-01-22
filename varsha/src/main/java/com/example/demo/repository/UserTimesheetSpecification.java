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
import com.example.demo.model.Timesheet;
import com.example.demo.model.Users;

public class UserTimesheetSpecification {
	public static Specification<Timesheet> findByEmail(String email){
		return new Specification<Timesheet>() {
			@Override
			public Predicate toPredicate(Root<Timesheet> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> emailPath = root.get("email");
				return cb.equal(emailPath, email);
			}
		};
	}
	
}




