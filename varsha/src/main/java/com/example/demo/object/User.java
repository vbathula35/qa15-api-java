package com.example.demo.object;

public class User extends UserCredentials {
	private String firstName;
	
	private String lastName;

	
	public User() {
		super();
		
	}
	
	public User(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		super.setEmail(email);
		super.setPassword(password);
	}


	/*
	 * public User(String firstName, String lastName, String email, String password)
	 * { super(); this.firstName = firstName; this.lastName = lastName; .email =
	 * email; //this.password = password; }
	 */

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

	
}
