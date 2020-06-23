/**
 * nasru - Jun 22, 2020
 * SecurityConfig.java 
 */
package com.mypractice.microservices.userapi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mypractice.microservices.userapi.service.SingUpService;

/**
 * @author nasru
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private Environment env;
	private BCryptPasswordEncoder bcryptPasswordEncode;
	private SingUpService usersService;
	public SecurityConfig(final Environment env,
			final BCryptPasswordEncoder bcryptPasswordEncode,
			final SingUpService usersService) {
		super();
		this.env = env;
		this.bcryptPasswordEncode = bcryptPasswordEncode;
		this.usersService = usersService;
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
//		http.authorizeRequests().antMatchers("/users/signup/**").permitAll()
		http.authorizeRequests().antMatchers("/**").hasIpAddress(env.getProperty("getway.ip"))
		.and()
		.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
	}

	/**
	 * @return
	 * @throws Exception 
	 */
	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		// TODO Auto-generated method stub
		AuthenticationFilter authenticationFilter = new AuthenticationFilter( env, usersService, authenticationManager());
		authenticationFilter.setFilterProcessesUrl(env.getProperty("signin.url.path"));
		return authenticationFilter;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersService).passwordEncoder(bcryptPasswordEncode);
		
	}
}
