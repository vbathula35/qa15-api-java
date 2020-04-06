package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "varsha_feature_permission_relatioship")
public class FeaturePermissionRelationship {
	@Id
	@Column(name = "relatiohshipCode")
	private String relatiohshipCode ;
	@Column(name = "featureCode")
	private String featureCode;
	@Column(name = "permissionCode")
	private String permissionCode;
	
	public String getRelatiohshipCode() {
		return relatiohshipCode;
	}
	public void setRelatiohshipCode(String relatiohshipCode) {
		this.relatiohshipCode = relatiohshipCode;
	}
	public String getFeatureCode() {
		return featureCode;
	}
	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}
	public String getPermissionCode() {
		return permissionCode;
	}
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	
	
}
