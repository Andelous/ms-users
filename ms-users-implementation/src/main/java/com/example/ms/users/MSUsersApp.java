package com.example.ms.users;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("")
public class MSUsersApp extends ResourceConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(MSUsersApp.class);

	public MSUsersApp() {
		LOGGER.info("Initializing USERS APP class...");

		packages("com.example.ms");

		LOGGER.info("Initialized USERS APP class successfully.");
	}
}
