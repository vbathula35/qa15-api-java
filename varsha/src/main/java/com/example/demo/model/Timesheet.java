package com.example.demo.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "user_timesheets")
public class Timesheet {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "date")
	private Date date;
	
	

	@Column(name = "day")
	private String day;
	
	@Column(name = "hours")
	private Integer hours;
	
	@Column(name = "notes")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String notes;
	
	@Column(name = "updatedBy")
	private String updatedBy;
	
	
	@Column(name = "notesUpdatedBy")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String notesUpdatedBy;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "isHolyday")
	private Boolean isHolyday;
	
	@Column(name = "week")
	private Integer week;
	
	@Column(name = "month")
	private Integer month;
	
	@Column(name = "year")
	private Integer year;
	
	@Column(name = "projectId")
	private int projectId;
	 
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getNotesUpdatedBy() {
		return notesUpdatedBy;
	}

	public void setNotesUpdatedBy(String notesUpdatedBy) {
		this.notesUpdatedBy = notesUpdatedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsHolyday() {
		return isHolyday;
	}

	public void setIsHolyday(Boolean isHolyday) {
		this.isHolyday = isHolyday;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	

	
	
		
}


