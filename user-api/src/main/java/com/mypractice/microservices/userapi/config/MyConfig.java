/**
 * nasru - Jun 21, 2020
 * MyConfig.java 
 */
package com.mypractice.microservices.userapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
