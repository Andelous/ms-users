package com.example.ms.users.def;

import com.example.ms.users.ex.ExistingUserException;
import com.example.ms.users.ex.IncorrectPasswordException;
import com.example.ms.users.ex.IncorrectUsernameException;
import com.example.ms.users.model.User;

public interface MSUsersDefinition {
	/**
	 * Adds a user to the user repository.
	 * 
	 * @param user User to be added. Not null.
	 * @throws ExistingUserException When the user passed to this method already
	 *                               exists (username must be unique).
	 */
	public void addUser(User user) throws ExistingUserException;

	/**
	 * Authenticates a user by using the username and password.
	 * 
	 * @param username
	 * @param password
	 * 
	 * @return Token that has 1 day expiration. Can be validated using
	 *         {@link #getUserByToken(String)}.
	 * 
	 * @throws IncorrectPasswordException When the user exists, but the password is
	 *                                    incorrect.
	 * @throws IncorrectUsernameException When the user doesn't exist.
	 */
	public String authenticateUser(String username, String password)
			throws IncorrectPasswordException, IncorrectUsernameException;

	/**
	 * Gets the user associated with the token provided. If the token has expired or
	 * is invalid, {@code null} will be returned.
	 * 
	 * @param token generated by {@link #authenticateUser(String, String)}.
	 * @return {@link User} associated with the token.
	 */
	public User getUserByToken(String token);
}
