package com.example.ms.users.def.model;

import java.util.Objects;

public class User {
	private String username;
	private String password;
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = Objects.requireNonNull(username, "The username can't be null").trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Objects.requireNonNull(password, "The password can't be null").trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
