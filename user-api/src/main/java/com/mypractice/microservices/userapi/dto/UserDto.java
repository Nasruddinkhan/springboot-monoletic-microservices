package com.mypractice.microservices.userapi.dto;

import java.util.HashSet;
import java.util.Set;

import com.mypractice.microservices.userapi.model.RoleResponseModel;

/**
 * nasru - Jun 20, 2020
 * UserDto.java 
 */
public final class UserDto {
	private String username;
	private String password;
	private String email;
	private Set<RoleResponseModel> roles = new HashSet<>();
	private String userid;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Set<RoleResponseModel>  getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleResponseModel>  roles) {
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
}
