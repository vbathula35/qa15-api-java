package com.example.demo.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Project;

public class ProjectSpecification {
	@SuppressWarnings("serial")
	public static Specification<Project> findByCreatedBy(String createdBy) {
		return new Specification<Project>() {
			@Override
			public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> emailPath = root.get("createdBy");
				return cb.equal(emailPath, createdBy);
			}
		};
	}
}
