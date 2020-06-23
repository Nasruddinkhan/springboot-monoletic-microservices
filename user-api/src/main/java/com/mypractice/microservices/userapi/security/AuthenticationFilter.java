/**
 * nasru - Jun 22, 2020
 * AuthenticationFilter.java 
 */
package com.mypractice.microservices.userapi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypractice.microservices.userapi.dto.UserDto;
import com.mypractice.microservices.userapi.model.LoginRequestModel;
import com.mypractice.microservices.userapi.service.SingUpService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author nasru
 *
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private Environment env;
	private SingUpService usersService;
	@Autowired
	public AuthenticationFilter(final Environment env,
			final SingUpService usersService,
			final AuthenticationManager authenticationManager ) {
		super();
		this.env = env;
		this.usersService = usersService;
		super.setAuthenticationManager(authenticationManager);
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		try {
			LoginRequestModel logRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);
			return getAuthenticationManager()
					.authenticate(new UsernamePasswordAuthenticationToken(logRequest.getEmail(),
							logRequest.getPassword(), new ArrayList<>()));
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain,
			Authentication auth) {

		String userName =	((User) auth.getPrincipal()).getUsername();
		UserDto userDetails =  usersService.getUserDetailsByEmail(userName);
		System.out.println(env.getProperty("token.expiration"));
		System.out.println(env.getProperty("token.secret"));
		//usersService.
		String token = Jwts.builder()
				  .setSubject(userDetails.getUserid())
				  .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(env.getProperty("token.expiration"))))
				  .signWith(SignatureAlgorithm.HS256, env.getProperty("token.secret"))
				  .compact();
		
		response.addHeader("userId", userDetails.getUserid());
		response.addHeader("token", token);
	}
}
