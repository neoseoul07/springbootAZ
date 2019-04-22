package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by yaswanth on 15/03/19.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//        This is to run as non-web application
//        new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE).run(args);
        SpringApplication.run(Application.class, args);
    }
}
