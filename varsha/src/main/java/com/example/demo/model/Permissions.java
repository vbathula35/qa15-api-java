package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "varsha_permissions")
public class Permissions {
	@Id
	@Column(name = "permissionCode")
	private String permissionCode ;
	@Column(name = "permissionName")
	private String permissionName;
	@Column(name = "permissionDescription")
	private String permissionDescription;
	@Column(name = "visibleInd")
	private boolean visibleInd;
	
	public String getPermissionCode() {
		return permissionCode;
	}
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionDescription() {
		return permissionDescription;
	}
	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}
	public boolean getVisibleInd() {
		return visibleInd;
	}
	public void setVisibleInd(boolean visibleInd) {
		this.visibleInd = visibleInd;
	}
	
}
