package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "varsha_user_auth")
public class UserAuth {
	@Id
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "registerDate")
	private Date registerDate;
	@Column(name = "pin")
	private Integer pin;
	@Column(name = "fPPin")
	private Integer fPPin;
	@Column(name = "userStatus")
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
	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	public Integer getfPPin() {
		return fPPin;
	}
	public void setfPPin(Integer fPPin) {
		this.fPPin = fPPin;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
}
