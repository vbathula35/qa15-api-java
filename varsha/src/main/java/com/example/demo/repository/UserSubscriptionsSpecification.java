package com.example.demo.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.UserSubscriptions;

public class UserSubscriptionsSpecification {
	public static Specification<UserSubscriptions> findByEmail(String email){
		return new Specification<UserSubscriptions>() {
			@Override
			public Predicate toPredicate(Root<UserSubscriptions> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> emailPath = root.get("email");
				return cb.equal(emailPath, email);
			}
		};
	}
}
