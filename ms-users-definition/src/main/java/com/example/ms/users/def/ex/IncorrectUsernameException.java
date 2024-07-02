package com.example.ms.users.def.ex;

public class IncorrectUsernameException extends MSUsersException {
	private static final long serialVersionUID = 1745090943627941650L;

	public IncorrectUsernameException(String username, String message, Throwable cause) {
		super(username, message, cause);
	}

}
