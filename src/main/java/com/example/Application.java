package com.example;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.apache.logging.log4j.LogManager;

/**
 * Created by yaswanth on 15/03/19.
 */
@SpringBootApplication
public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
//        This is to run as non-web application
//        new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE).run(args);
        SpringApplication.run(Application.class, args);

        LOGGER.info("Info level log message");
        LOGGER.debug("Debug level log message");
        LOGGER.error("Error level log message");
    }
}
