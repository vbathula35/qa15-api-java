package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.demo.model.UserProjectRelationship;

public interface UserProjectRelationshipRepository extends JpaRepository<UserProjectRelationship, Integer>, JpaSpecificationExecutor<UserProjectRelationship> {

}



