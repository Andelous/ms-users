package com.example.ms.users;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.ms.users.def.MSUsers;
import com.example.ms.users.def.ex.ExistingUserException;
import com.example.ms.users.def.ex.IncorrectPasswordException;
import com.example.ms.users.def.ex.IncorrectUsernameException;
import com.example.ms.users.def.model.User;

public class MSUsersClient implements MSUsers {
	private static final Logger LOGGER = LoggerFactory.getLogger(MSUsersClient.class);

	private final Client client;
	private final WebTarget targetBase;
	private final WebTarget targetAddUser;
	private final WebTarget targetAuthenticateUser;

	public MSUsersClient(String baseURI) {
		client = ClientBuilder.newClient();
		
		client.register(JSO)
		
		targetBase = client.target(baseURI);
		targetAddUser = targetBase.path("users");
		targetAuthenticateUser = targetBase.path("users").path("auth");
	}

	@Override
	public void addUser(User user) throws ExistingUserException {
		Response response = targetAddUser.request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(user, MediaType.APPLICATION_JSON));

		StatusType status = response.getStatusInfo();
		String exceptionType = response.getHeaderString("Exception-Type");
		String responseBody = response.readEntity(String.class);

		boolean isSuccess = status == Status.ACCEPTED || status == Status.OK || status == Status.NO_CONTENT;
		boolean isClientError = status == Status.BAD_REQUEST;
		boolean isServerError = !isSuccess && !isClientError;

		response.close();

		if (isSuccess)
			return;

		if (isClientError)
			switch (exceptionType) {

			case "ExistingUserException":
				throw new ExistingUserException(user.getUsername(), responseBody, null);

			default:
				throw new RuntimeException("UNRECOGNIZED EXCEPTION FROM SERVER [" + exceptionType + "]. Status code ["
						+ status + "] - Message from server: " + responseBody);

			}

		if (isServerError)
			throw new RuntimeException(
					"Error while calling server. Status code [" + status + "] - Message from server: " + responseBody);
	}

	@Override
	public String authenticateUser(String username, String password)
			throws IncorrectPasswordException, IncorrectUsernameException {
		return null;
	}

	@Override
	public User getUserByToken(String token) {
		return null;
	}

	public static void main(String[] args) throws ExistingUserException {
		MSUsersClient client = new MSUsersClient("http://localhost:8080/ms-users-service/");

		User user = new User("andelous", "mexico2005");

		client.addUser(user);
	}

}
