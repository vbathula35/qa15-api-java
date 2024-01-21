package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payments", catalog="varsha")
public class Payments {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "paymentId")
	private int paymentId;
	
	@Column(name = "totalAmount")
	private int totalAmount;
	
	@Column(name = "takeHome")
	private int takeHome;
	
	@Column(name = "tax")
	private int tax;
	
	@Column(name = "federalTax")
	private int federalTax;
	
	@Column(name = "stateTax")
	private int stateTax;
	
	@Column(name = "otherTax")
	private int otherTax;
	
	@Column(name = "otherPayment")
	private int otherPayment;
	
	@Column(name = "comments")
	private String comments;

	
	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTakeHome() {
		return takeHome;
	}

	public void setTakeHome(int takeHome) {
		this.takeHome = takeHome;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public int getFederalTax() {
		return federalTax;
	}

	public void setFederalTax(int federalTax) {
		this.federalTax = federalTax;
	}

	public int getStateTax() {
		return stateTax;
	}

	public void setStateTax(int stateTax) {
		this.stateTax = stateTax;
	}

	public int getOtherTax() {
		return otherTax;
	}

	public void setOtherTax(int otherTax) {
		this.otherTax = otherTax;
	}

	public int getOtherPayment() {
		return otherPayment;
	}

	public void setOtherPayment(int otherPayment) {
		this.otherPayment = otherPayment;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
}