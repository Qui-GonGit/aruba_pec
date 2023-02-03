package com.aruba.pec.controller.dto;

import javax.validation.constraints.NotNull;

public class UserWebRequest {
	@NotNull(message = "the filed email can't be null")
	String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
