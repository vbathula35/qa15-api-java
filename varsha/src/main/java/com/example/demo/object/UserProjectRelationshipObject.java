package com.example.demo.object;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserProjectRelationshipObject {
	private int id;
	private int projectId;
	private String email;
	private Boolean isAdmin; 
	private String projectStartDate;
	private String projectEndDate;
	private int payRate;
	private int employerPercentage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public String getProjectEndDate() {
		return projectEndDate;
	}
	public void setProjectEndDate(String projectEndDate) {
		this.projectEndDate = projectEndDate;
	}
	public int getPayRate() {
		return payRate;
	}
	public void setPayRate(int payRate) {
		this.payRate = payRate;
	}
	public int getEmployerPercentage() {
		return employerPercentage;
	}
	public void setEmployerPercentage(int employerPercentage) {
		this.employerPercentage = employerPercentage;
	}
	
	
}
