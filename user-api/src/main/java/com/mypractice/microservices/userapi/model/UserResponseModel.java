/**
 * nasru - Jun 22, 2020
 * UserResponseModel.java 
 */
package com.mypractice.microservices.userapi.model;

/**
 * @author nasru
 *
 */
public final class UserResponseModel {
	private String username;
	private String password;
	private String email;
	private String userid;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
