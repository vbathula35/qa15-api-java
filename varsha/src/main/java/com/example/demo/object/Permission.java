package com.example.demo.object;

import javax.persistence.Column;

public class Permission {
	private String permissionCode;
	private String permissionName;
	private String permissionDescription;
	private boolean visibleInd;
	private boolean defaultInd;
	
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
	public boolean isVisibleInd() {
		return visibleInd;
	}
	public void setVisibleInd(boolean visibleInd) {
		this.visibleInd = visibleInd;
	}
	public boolean isDefaultInd() {
		return defaultInd;
	}
	public void setDefaultInd(boolean defaultInd) {
		this.defaultInd = defaultInd;
	}
	
	
}
