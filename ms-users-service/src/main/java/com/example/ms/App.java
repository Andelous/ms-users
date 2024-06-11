package com.example.ms;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationPath("")
public class App extends ResourceConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public App() {
		LOGGER.info("Initializing APP class...");

		packages("com.example.ms");
	}
}
