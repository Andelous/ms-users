package com.example.ms.users;

import java.util.Properties;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.ms.users.db.UsersRepository;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("")
public class MSUsersApp extends ResourceConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(MSUsersApp.class);

	private static final String STR_PROPERTIES = "ms-users.properties";
	private static final Properties PROPERTIES = new Properties();

	public MSUsersApp() throws Exception {
		LOGGER.info("Initializing USERS APP class...");

		packages("com.example.ms");

		LOGGER.info("Loading properties from classpath file [{}] ...", STR_PROPERTIES);

		PROPERTIES.load(MSUsersApp.class.getClassLoader().getResourceAsStream(STR_PROPERTIES));

		LOGGER.info("Loaded correctly the application properties.");
		LOGGER.info("Initializing the Users Repository...");

		UsersRepository.init();

		LOGGER.info("Initialized Users Repository successfully.");
		LOGGER.info("Initialized USERS APP class successfully.");
	}

	public static Properties getApplicationProperties() {
		return PROPERTIES;
	}
}
