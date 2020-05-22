package com.example.demo.constant;

public class AppConstant {
	public static final String COUNTRY = "USA";
	public static final String ACTIVE_USER = "active";
	public static final String PENDING_USER = "pending";
	public static final String RESET_PASSWORD = "resetpassword";
	public static final String INACTIVE_USER = "inactive";
	public static final String LOCKED_USER = "locked";
	public static final String SUPER_ADMIN = "SPADMN";
	public static final String ADMIN = "RGADMN";
	public static final String REG_USER = "REGUSR";
	
	public static final String UNAUTH_USER = "Unauthorized User.";
	public static final String USER_NO_PERMISSIONS = "User do not have permissions.";
	
	public enum TASK_STATUS {
		PENDING("pending"), 
		DONE("done"), 
		EXPIRED("expired");
		private String value;
		public String getValue() {
			return value;
		}
		private TASK_STATUS(String value) {
			this.value = value;
		}
	}  
	
}
