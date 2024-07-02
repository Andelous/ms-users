package com.example.ms.users.def.ex;

public class IncorrectPasswordException extends MSUsersException {
	private static final long serialVersionUID = 3100412705937036106L;

	public IncorrectPasswordException(String username, String message, Throwable cause) {
		super(username, message, cause);
	}

}
