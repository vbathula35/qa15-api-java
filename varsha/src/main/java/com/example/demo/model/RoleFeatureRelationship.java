package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "varsha_role_feature_relatioship")
public class RoleFeatureRelationship {
	@Id
	@Column(name = "relatiohshipCode")
	private String relatiohshipCode ;
	@Column(name = "roleCode")
	private String roleCode;
	@Column(name = "featureCode")
	private String featureCode;
	@Column(name = "visibleInd")
	private boolean visibleInd;
	
	
	
	public String getRelatiohshipCode() {
		return relatiohshipCode;
	}
	public void setRelatiohshipCode(String relatiohshipCode) {
		this.relatiohshipCode = relatiohshipCode;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getFeatureCode() {
		return featureCode;
	}
	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}
	public boolean isVisibleInd() {
		return visibleInd;
	}
	public void setVisibleInd(boolean visibleInd) {
		this.visibleInd = visibleInd;
	}
	
	
}
