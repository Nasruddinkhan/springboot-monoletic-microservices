package com.mypractice.microservices;
/**
 * nasru - Jun 20, 2020
 * UserApiApplication.java 
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients
public class UserApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UserApiApplication.class, args);
	}
}
