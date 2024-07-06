package com.example.ms.users;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationPath("")
public class UsersApp extends ResourceConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersApp.class);

	public UsersApp() {
		LOGGER.info("Initializing USERS APP class...");

		packages("com.example.ms");

		LOGGER.info("Initialized USERS APP class successfully.");
	}
}
