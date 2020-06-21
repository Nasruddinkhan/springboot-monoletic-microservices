package com.mypractice.microservices.userapi.model;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * nasru - Jun 20, 2020
 * UserCreationModel.java 
 */
@XmlRootElement
public final class UserCreationModel {
	private String username;
	private String password;
	private String email;
	private Set<String> roles;
	private String userid;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
