package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import com.example.demo.object.TimesheetRequest;

@Entity
@Table(name = "timesheets", catalog="varsha")
public class UserTimesheets {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "projectId")
	private int projectId;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "month")
	private int month;
	
	@Column(name = "timesheet", nullable = false)
	@Nationalized 
	private String timesheet;

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

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(String timesheet) {
		this.timesheet = timesheet;
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
