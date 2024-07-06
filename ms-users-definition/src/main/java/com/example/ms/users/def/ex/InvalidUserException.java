package com.example.ms.users.def.ex;

public class InvalidUserException extends MSUsersException {
	private static final long serialVersionUID = 1055652155053180817L;

	public InvalidUserException(String username, String message, Throwable cause) {
		super(username, message, cause);
	}
}
