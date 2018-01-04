package com.example.jersey.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootJerseyApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(SpringbootJerseyApplication.class);

//		Properties properties = new Properties();
//		properties.setProperty("spring.resources.static-locations", "classpath:/static/");
//		app.setDefaultProperties(properties);
		app.run(args);
	}
}
