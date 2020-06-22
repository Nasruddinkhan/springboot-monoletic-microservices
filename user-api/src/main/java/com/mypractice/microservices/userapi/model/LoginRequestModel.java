/**
 * nasru - Jun 22, 2020
 * LoginRequestModel.java 
 */
package com.mypractice.microservices.userapi.model;

/**
 * @author nasru
 *
 */
public final class LoginRequestModel {
	private String password;
	private String email;
	@Override
	public String toString() {
		return "LoginRequestModel [password=" + password + ", email=" + email + "]";
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
