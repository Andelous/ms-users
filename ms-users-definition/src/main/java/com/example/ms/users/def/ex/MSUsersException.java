package com.example.ms.users.def.ex;

import java.util.Objects;

public abstract class MSUsersException extends Exception {
	private static final long serialVersionUID = 2607026625826786245L;

	private String username;

	public MSUsersException(String username, String message, Throwable cause) {
		super(message, cause);

		this.username = Objects.requireNonNull(username, "The username can't be null");
	}

	public String getUsername() {
		return username;
	}
}
