package com.example.demo.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.UserEvent;

public class UserEventSpecification {
	public static Specification<UserEvent> findByEmail(String email){
		return new Specification<UserEvent>() {
			@Override
			public Predicate toPredicate(Root<UserEvent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> emailPath = root.get("email");
				return cb.equal(emailPath, email);
			}
		};
	}
}
