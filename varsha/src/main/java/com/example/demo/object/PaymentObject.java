package com.example.demo.object;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonInclude;

public class PaymentObject {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int paymentId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private double totalAmount;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int takeHome;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int tax;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int federalTax;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int stateTax;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int otherTax;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int otherPayment;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String comments;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int projectId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String email;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int year;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int month;
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
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
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}

	
}
