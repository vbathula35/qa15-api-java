package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.PaymentProjectRelationship;
import com.example.demo.model.Payments;

public interface PaymentProjectRelationshipRepository extends JpaRepository<PaymentProjectRelationship, Integer> , JpaSpecificationExecutor<PaymentProjectRelationship> {

	@Query ("SELECT ppr FROM PaymentProjectRelationship ppr WHERE ppr.projectId = :projectId")
	public Page<?> findPaymentsByProjectId(Pageable pageable, int projectId);
	
	@Query ("SELECT ppr FROM PaymentProjectRelationship ppr WHERE ppr.projectId = :projectId AND ppr.email = :email")
	public Page<?> findPaymentsByProjectIdAndEmail(Pageable pageable, int projectId, String email);
	
	@Query ("SELECT ppr FROM PaymentProjectRelationship ppr WHERE ppr.projectId = :projectId AND ppr.email = :email")
	public List<PaymentProjectRelationship> findPaymentsByProjectIdAndEmailWithOutPagination(int projectId, String email);
	
	@Transactional
	@Modifying
	@Query ("DELETE FROM PaymentProjectRelationship ppr WHERE ppr.paymentId = :paymentId")
	int deletePaymentByPaymentId(int paymentId);

}
