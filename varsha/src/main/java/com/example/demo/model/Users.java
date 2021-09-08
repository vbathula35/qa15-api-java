package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "varsha_user")

public class Users {
	@Id
	@Column(name = "email")
	private String email;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	
	@Column (name = "userRole")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String userRole;
	
	@Column (name = "addressLine1")
	private String addressLine1;
	
	@Column (name = "addressLine2")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String addressLine2;
	
	@Column (name = "city")
	private String city;
	@Column (name = "state")
	private String state;
	
	@Column (name = "country")
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String country;
	
	@Column (name = "zipCode")
	private Integer zipCode;
	@Column (name = "phoneNumber")
	private String phoneNumber;

	
	@OneToOne
	@JoinColumn(name = "email", referencedColumnName = "email", insertable = false , updatable = true)
	private UserAuth userAuth;
	
	public UserAuth getUserAuth() {
		return userAuth;
	}

	public void setUserAuth(UserAuth userAuth) {
		this.userAuth = userAuth;	
	}
	
	public Users() {
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	@Override
	public String toString() {
		return "Users [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
