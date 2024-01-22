package com.example.demo.object;

import java.util.List;

public class Feature {
	private String featureCode;
	private String featureName;
	private String featureDescription;
	private boolean visibleInd;
	private List<Permission> permissions;
	
	public String getFeatureCode() {
		return featureCode;
	}
	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getFeatureDescription() {
		return featureDescription;
	}
	public void setFeatureDescription(String featureDescription) {
		this.featureDescription = featureDescription;
	}
	public boolean isVisibleInd() {
		return visibleInd;
	}
	public void setVisibleInd(boolean visibleInd) {
		this.visibleInd = visibleInd;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
	
}
