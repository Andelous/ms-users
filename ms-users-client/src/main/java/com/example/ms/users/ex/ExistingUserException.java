package com.example.ms.users.ex;

public class ExistingUserException extends MSUsersException {
	private static final long serialVersionUID = 7976600308495991764L;

	public ExistingUserException(String username, String message, Throwable cause) {
		super(username, message, cause);
	}
}