package org.music.app.codes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SbcbackendApplication {
	public static final Logger LOGGER = LogManager.getLogger(SbcbackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SbcbackendApplication.class, args);
		LOGGER.info("SBC Backend Starts");
		
	}

}
