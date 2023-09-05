package com.example.demo.object;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

public class TimesheetRequest {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String email;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String date;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String day;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer hours;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean isHolyday;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String notes;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String startDate;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String endDate;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer week;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer month;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer year;

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
	public Boolean getIsHolyday() {
		return isHolyday;
	}
	public void setIsHolyday(Boolean isHolyday) {
		this.isHolyday = isHolyday;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	

	
	
	
}
