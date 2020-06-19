package com.microserices.integration.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class IntegrationAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrationAppApiApplication.class, args);
	}

}
