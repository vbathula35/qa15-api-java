package com.example.demo.repository;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Payments;
import com.example.demo.model.Project;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> , JpaSpecificationExecutor<Payments> {
	@Transactional
	@Modifying
	@Query ("UPDATE Payments SET takeHome = :takeHome where paymentId = :paymentId")
	void updateStatusById(Integer paymentId, int takeHome);
	
	
	Optional<Payments> findByPaymentId(int paymentId);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE Payments p SET p.totalAmount = :totalAmount, p.tax = :tax, p.takeHome = :takeHome, p.stateTax = :stateTax, p.otherTax = :otherTax, "
			+ "p.otherPayment = :otherPayment, p.federalTax = :federalTax, p.comments = :comments  WHERE p.paymentId = :paymentId")
	int updatePaymentByPaymentId(int totalAmount, int tax, int takeHome, int stateTax, int otherTax, int otherPayment, int federalTax, String comments, int paymentId);
	
	
	@Transactional
	@Modifying
	@Query ("DELETE FROM Payments p WHERE p.paymentId = :paymentId")
	int deletePaymentByPaymentId(int paymentId);
	
	@Query("SELECT p FROM Payments p "
			+ "JOIN PaymentProjectRelationship ppr "
			+ "ON p.paymentId = ppr.paymentId "
			+ "WHERE ppr.projectId = :projectId AND ppr.email = :email AND ppr.month = :month AND ppr.year = :year")
	public Payments findByUserAndProjectId(int projectId, int month, int year, String email);

}