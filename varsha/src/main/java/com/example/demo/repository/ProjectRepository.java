package com.example.demo.repository;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.Project;

public interface ProjectRepository extends JpaRepository<Project, String> , JpaSpecificationExecutor<Project>{
	@Transactional
	@Modifying
	@Query ("UPDATE Project SET projectDescription = :desc where id = :id")
	void updateStatusById(Integer id, String desc);
	
	
	@Query("SELECT p FROM Project p JOIN UserProjectRelationship up ON p.id = p.id WHERE up.email = :email")
	public Page<Project> findProjectsByEmail(String email, Pageable pageable);
}

