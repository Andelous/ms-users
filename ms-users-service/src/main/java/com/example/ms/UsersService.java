package com.example.ms;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("users")
public class UsersService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersService.class);

	@GET
	@Produces("text/plain")
	public String meow() {
		LOGGER.info("Meow!!!");
		return "I'm meowing!";
	}
}
