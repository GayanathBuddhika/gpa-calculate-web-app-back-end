package com.gpastm.gpa.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
	
	private final String jwt;
	private User user;
	
	
	
	public AuthenticationResponse(String jwt, User user) {
		super();
		this.jwt = jwt;
		this.user = user;
	}
	

	public User getUser() {
		return user;
	}

	public String getJwt() {
		return jwt;
	}
	
	

}
