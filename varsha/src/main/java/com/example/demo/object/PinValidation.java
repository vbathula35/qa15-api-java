package com.example.demo.object;

import com.fasterxml.jackson.annotation.JsonInclude;

public class PinValidation {
	private String email;
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private Long pin;
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private Long fpin;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPin() {
		return pin;
	}
	public void setPin(Long pin) {
		this.pin = pin;
	}
	public Long getFpin() {
		return fpin;
	}
	public void setFpin(Long fpin) {
		this.fpin = fpin;
	}
	
	
}
