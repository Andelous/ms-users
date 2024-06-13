package com.example.ms.users;

import java.util.Objects;

public class User {
	private String username;
	private String password;
	private String email;

	public User(String username, String password) {
		Objects.requireNonNull(username, "The username can't be null");
		Objects.requireNonNull(password, "The password can't be null");

		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
