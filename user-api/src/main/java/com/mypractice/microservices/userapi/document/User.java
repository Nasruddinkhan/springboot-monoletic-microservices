package com.mypractice.microservices.userapi.document;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mypractice.microservices.userapi.model.RoleResponseModel;
/**
 * nasru - Jun 20, 2020
 * User.java 
 */
@Document(collection = "users")
public class User {
	@Id
	private String userid;
	private String username;
	@Indexed(unique=true, sparse=true)
	private String email;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private Set<RoleResponseModel> roles = new HashSet<>();
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<RoleResponseModel> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleResponseModel> roles) {
		this.roles = roles;
	}

}
