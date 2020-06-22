/**
 * nasru - Jun 20, 2020
 * SingUpService.java 
 */
package com.mypractice.microservices.userapi.service;

import com.mypractice.microservices.userapi.dto.UserDto;

/**
 * @author nasru
 *
 */
public interface SingUpService extends UserSerivice{

	/**
	 * @param userDto
	 * @return
	 */
	UserDto createUser(UserDto userDto);
	UserDto getUserDetailsByEmail(String email);
}
