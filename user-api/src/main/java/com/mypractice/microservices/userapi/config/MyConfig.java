/**
 * nasru - Jun 21, 2020
 * MyConfig.java 
 */
package com.mypractice.microservices.userapi.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * @author nasru
 *
 */
@Configuration
public class MyConfig {

	public MyConfig() {
		super();
	}
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
		        .setReadTimeout(Duration.ofSeconds(30))
		        .build();
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
