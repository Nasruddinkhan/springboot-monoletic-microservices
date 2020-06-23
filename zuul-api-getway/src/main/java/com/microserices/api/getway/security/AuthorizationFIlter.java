/**Jun 23, 2020
 * nasru - AuthorizationFIlter.java
 */
package com.microserices.api.getway.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

/**
 * @author nasru
 *
 */
public class AuthorizationFIlter extends BasicAuthenticationFilter {

	private Environment env;

	/**
	 * @param authenticationManager
	 * @param authenticationEntryPoint
	 */
	public AuthorizationFIlter(AuthenticationManager authenticationManager, final Environment env) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
		this.env = env;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String authorizationHeader = request.getHeader(env.getProperty("authorization.token.header.name"));
		
		if (authorizationHeader == null
				|| !authorizationHeader.startsWith(env.getProperty("authorization.token.header.prefix"))) {
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken  authentication = getAuthentication(request);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	/**
	 * @param request
	 * @return
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String authorizationHeader = request.getHeader(env.getProperty("authorization.token.header.name"));
		if (authorizationHeader == null)
			return null;
		String token = authorizationHeader.replaceAll(env.getProperty("authorization.token.header.prefix"), "");
		System.out.println(env.getProperty("token.secret"));
		String userID = Jwts.parser()
				  .setSigningKey(env.getProperty("token.secret"))
				  .parseClaimsJws(token)
				  .getBody()
				  .getSubject();
		if ( userID == null ) {
			return null;
		}
		return new UsernamePasswordAuthenticationToken(userID, null, new ArrayList<>());
	}
}
