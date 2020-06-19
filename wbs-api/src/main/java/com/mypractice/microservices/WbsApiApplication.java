package com.mypractice.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WbsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WbsApiApplication.class, args);
	}

}
