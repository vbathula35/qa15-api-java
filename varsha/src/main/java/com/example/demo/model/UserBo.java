package com.example.demo.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserBo {

	private String firstName;
	private String lastName;
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String userRole;
	private String addressLine1;
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String addressLine2;
	private String city;
	private String state;
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String country;
	private Integer zipCode;
	private String phoneNumber;

	private String email;
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private Date registerDate;
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private Long pin;
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private Long fPPin;
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String userStatus;
	
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private List<String> userFeatures;
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private List<String> userPermissions;


	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Long getPin() {
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
	
	public List<String> getUserFeatures() {
		return userFeatures;
	}

	public void setUserFeatures(List<String> list) {
		this.userFeatures = list;
	}

	public List<String> getUserPermissions() {
		return userPermissions;
	}

	public void setUserPermissions(List<String> userPermissions) {
		this.userPermissions = userPermissions;
	}


}
