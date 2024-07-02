package com.example.ms.users;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.ms.users.def.MSUsers;
import com.example.ms.users.def.ex.ExistingUserException;
import com.example.ms.users.def.ex.IncorrectPasswordException;
import com.example.ms.users.def.ex.IncorrectUsernameException;
import com.example.ms.users.def.model.User;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@Path("users")
public class UsersService implements MSUsers {
	private static final Logger LOGGER = LoggerFactory.getLogger(MSUsers.class);

	private static final Map<String, User> MAP_USERS = new HashMap<>();
	private static final Cache<String, User> CACHE_AUTH = Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES)
			.maximumSize(100).build();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String meow() {
		LOGGER.info("Meow!!!");
		return "I'm meowing!";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void addUser(User user) throws ExistingUserException {
		if (MAP_USERS.containsKey(user.getUsername())) {
			throw new ExistingUserException(user.getUsername(), "User already exists [" + user.getUsername() + "]",
					null);
		}

		MAP_USERS.put(user.getUsername(), user);
	}

	@Override
	public String authenticateUser(String username, String password)
			throws IncorrectPasswordException, IncorrectUsernameException {

		User user = MAP_USERS.get(username);

		if (user == null)
			throw new IncorrectUsernameException(username, "The user [" + username + "] doesn't exist", null);

		if (!user.getPassword().equals(password))
			throw new IncorrectPasswordException(username, "Incorrect password for user [" + username + "]", null);

		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString();

		CACHE_AUTH.put(uuidStr, user);

		return uuidStr;
	}

	@Override
	public User getUserByToken(String token) {
		return CACHE_AUTH.getIfPresent(token);
	}
}
