package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "varsha_user_auth")
public class UserAuth {
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "registerDate")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private Date registerDate;
	
	@Column(name = "pin")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private Long pin;
	
	@Column(name = "fPPin")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private Long fPPin;
	
	@Column(name = "userStatus")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String userStatus;
	
	
	public UserAuth() {
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public long getPin() {
		return pin;
	}
	public void setPin(Long pin) {
		this.pin = pin;
	}
	public Long getfPPin() {
		return fPPin;
	}
	public void setfPPin(Long fPPin) {
		this.fPPin = fPPin;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
}
