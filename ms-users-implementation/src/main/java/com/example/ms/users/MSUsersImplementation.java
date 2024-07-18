package com.example.ms.users;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.ms.users.db.UsersRepository;
import com.example.ms.users.def.MSUsers;
import com.example.ms.users.def.ex.ExistingUserException;
import com.example.ms.users.def.ex.IncorrectPasswordException;
import com.example.ms.users.def.ex.IncorrectUsernameException;
import com.example.ms.users.def.ex.InvalidUserException;
import com.example.ms.users.def.model.User;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("users")
public class MSUsersImplementation implements MSUsers {
	private static final Logger LOGGER = LoggerFactory.getLogger(MSUsers.class);

	private static final Cache<String, User> CACHE_AUTH = Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES)
			.maximumSize(100).build();

	@GET
	public void meow() {
		LOGGER.info("Meow!!!");
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void addUser(User user) throws ExistingUserException, InvalidUserException {
		if (user == null)
			throw new InvalidUserException(null,
					"The user can't be null -- please provide a correct JSON representation.", null);

		if (user.getPassword() == null || user.getPassword().isEmpty() || user.getUsername() == null
				|| user.getUsername().isEmpty())
			throw new InvalidUserException(user.getUsername(),
					"To register a user, both a USERNAME and a PASSWORD must be provided.", null);

		if (UsersRepository.exists(user.getUsername())) {
			throw new ExistingUserException(user.getUsername(), "User already exists [" + user.getUsername() + "]",
					null);
		}

		LOGGER.info("Registering new user [{}]", user.getUsername());

		UsersRepository.insert(user);
	}

	@POST
	@Path("auth")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	@Override
	public String authenticateUser(@FormParam("username") String username, @FormParam("password") String password)
			throws IncorrectPasswordException, IncorrectUsernameException {

		User user = UsersRepository.select(username);

		if (user == null)
			throw new IncorrectUsernameException(username, "The user [" + username + "] doesn't exist", null);

		if (!user.getPassword().equals(password))
			throw new IncorrectPasswordException(username, "Incorrect password for user [" + username + "]", null);

		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString();

		CACHE_AUTH.put(uuidStr, user);

		return uuidStr;
	}

	@GET
	@Path("auth/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public User getUserByToken(@PathParam("token") String token) {
		return CACHE_AUTH.getIfPresent(token);
	}
}
