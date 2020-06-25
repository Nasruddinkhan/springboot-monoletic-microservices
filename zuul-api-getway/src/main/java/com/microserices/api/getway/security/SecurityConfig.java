/**Jun 23, 2020
 * nasru - SecurityConfig.java
 */
package com.microserices.api.getway.security;

import org.apache.http.client.methods.HttpPost;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author nasru
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private Environment env;
	public SecurityConfig(final Environment env) {
		super();
		this.env = env;
	}
	@Override
	protected void configure(HttpSecurity  http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests() 
		
            .antMatchers(env.getProperty("user-ws.vc.api-docs"),
	            		env.getProperty("user-ws.ui.api-configuration"), 
	            		env.getProperty("user-ws.swagger.resources"), 
	            		env.getProperty("user-ws.configuration.security"), 
	            		env.getProperty("user-ws.ui.swagger")).permitAll()
			.antMatchers(env.getProperty("user-ws.actuator.path")).permitAll()
			.antMatchers(env.getProperty("api-getway.actuator.path")).permitAll()
			.antMatchers(HttpMethod.POST, env.getProperty("api.signin.path")).permitAll()
			.antMatchers(HttpMethod.POST, env.getProperty("api.signup.path")).permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilter(new AuthorizationFIlter(authenticationManager(), env));
	}
}
