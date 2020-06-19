package com.microserices.masterapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MasterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterApiApplication.class, args);
	}

}
