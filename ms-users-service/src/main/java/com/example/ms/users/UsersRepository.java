package com.example.ms.users;

import java.util.HashMap;
import java.util.Map;

import com.example.ms.users.User;

public class UsersRepository {
	private static final Map<String, User> MAP_USERS = new HashMap<>();

	public static void addUser(User user) throws ExistingUserException {
		if (MAP_USERS.containsKey(user.getUsername())) {
			throw new ExistingUserException("User already exists [" + user.getUsername() + "]", user.getUsername());
		}

		MAP_USERS.put(user.getUsername(), user);
	}

	/**
	 * Exception thrown when the user already exists.
	 * 
	 * @author angel
	 */
	public static class ExistingUserException extends Exception {
		private static final long serialVersionUID = 7976600308495991764L;

		private String username;

		public ExistingUserException(String message, String username) {
			super(message);

			this.username = username;
		}

		public String getUsername() {
			return username;
		}
	}
}
