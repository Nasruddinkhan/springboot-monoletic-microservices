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
public interface SingUpService {

	/**
	 * @param userDto
	 * @return
	 */
	UserDto createUser(UserDto userDto);

}
