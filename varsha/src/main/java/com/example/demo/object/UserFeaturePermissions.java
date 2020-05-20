package com.example.demo.object;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserFeaturePermissions {
	private String email;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String role;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> features;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> permissions;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<String> getFeatures() {
		return features;
	}
	public void setFeatures(List<String> features) {
		this.features = features;
	}
	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	
	
}
