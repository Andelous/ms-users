package com.example.ms.users;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
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
import com.example.ms.users.def.ex.InvalidUserException;
import com.example.ms.users.def.model.User;

public class MSUsersClient implements MSUsers {
	private static final Logger LOGGER = LoggerFactory.getLogger(MSUsersClient.class);

	private final Client client;
	private final WebTarget targetBase;
	private final WebTarget targetAddUser;
	private final WebTarget targetAuthenticateUser;

	public MSUsersClient(String baseURI) {
		client = ClientBuilder.newClient();

		targetBase = client.target(baseURI);
		targetAddUser = targetBase.path("users");
		targetAuthenticateUser = targetBase.path("users").path("auth");
	}

	@Override
	public void addUser(User user) throws ExistingUserException, InvalidUserException {
		Response response = targetAddUser.request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(user, MediaType.APPLICATION_JSON));

		StatusType status = response.getStatusInfo();
		String exceptionType = response.getHeaderString("Exception-Type");
		String responseBody = response.readEntity(String.class);

		boolean isSuccess = status == Status.ACCEPTED || status == Status.OK || status == Status.NO_CONTENT;
		boolean isClientError = status == Status.BAD_REQUEST;
		boolean isServerError = !isSuccess && !isClientError;

		response.close();

		if (isSuccess) {
			LOGGER.info("Registered successfully the user [{}]", user.getUsername());
			return;
		}

		if (isClientError)
			switch (exceptionType) {

			case "ExistingUserException":
				throw new ExistingUserException(user.getUsername(), responseBody, null);

			case "InvalidUserException":
				throw new InvalidUserException(user.getUsername(), responseBody, null);

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
		Form form = new Form();
		form.param("username", username);
		form.param("password", password);

		Response response = targetAuthenticateUser.request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));

		StatusType status = response.getStatusInfo();
		String exceptionType = response.getHeaderString("Exception-Type");
		String responseBody = response.readEntity(String.class);

		boolean isSuccess = status == Status.ACCEPTED || status == Status.OK || status == Status.NO_CONTENT;
		boolean isClientError = status == Status.BAD_REQUEST;
		boolean isServerError = !isSuccess && !isClientError;

		response.close();

		if (isSuccess) {
			LOGGER.info("User [{}] successfully logged in.", username);
			return responseBody;
		}

		if (isClientError)
			switch (exceptionType) {

			case "IncorrectPasswordException":
				throw new IncorrectPasswordException(username, responseBody, null);

			case "IncorrectUsernameException":
				throw new IncorrectUsernameException(username, responseBody, null);

			default:
				throw new RuntimeException("UNRECOGNIZED EXCEPTION FROM SERVER [" + exceptionType + "]. Status code ["
						+ status + "] - Message from server: " + responseBody);

			}

		if (isServerError)
			throw new RuntimeException(
					"Error while calling server. Status code [" + status + "] - Message from server: " + responseBody);

		throw new RuntimeException("The END OF THE WORLD IS HERE -- this should never be thrown.");
	}

	@Override
	public User getUserByToken(String token) {
		WebTarget target = targetAuthenticateUser.path(token);

		Response response = target.request(MediaType.APPLICATION_JSON).get();

		StatusType status = response.getStatusInfo();

		boolean isSuccess = status == Status.ACCEPTED || status == Status.OK || status == Status.NO_CONTENT;
		boolean isServerError = !isSuccess;

		if (isSuccess) {
			User responseUser = response.readEntity(User.class);
			response.close();

			return responseUser;
		}

		if (isServerError) {
			String responseBody = response.readEntity(String.class);
			response.close();

			throw new RuntimeException(
					"Error while calling server. Status code [" + status + "] - Message from server: " + responseBody);
		}

		throw new RuntimeException("The END OF THE WORLD IS HERE -- this should never be thrown.");
	}
}
