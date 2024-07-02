package com.example.ms.users;

import javax.ws.rs.client.ClientBuilder;

import com.example.ms.users.def.MSUsersDefinition;
import com.example.ms.users.ex.ExistingUserException;
import com.example.ms.users.ex.IncorrectPasswordException;
import com.example.ms.users.ex.IncorrectUsernameException;
import com.example.ms.users.model.User;

public class MSUsersClient implements MSUsersDefinition {

	@Override
	public void addUser(User user) throws ExistingUserException {
		// TODO Auto-generated method stub
		ClientBuilder.newClient();
	}

	@Override
	public String authenticateUser(String username, String password)
			throws IncorrectPasswordException, IncorrectUsernameException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
