package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "varsha_user_features")
public class UserFeatures {
	@GeneratedValue
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "email")
	private String email ;
	@Column(name = "featureCode")
	private String featureCode;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFeatureCode() {
		return featureCode;
	}
	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}
}
