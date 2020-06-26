package com.mypractice.microservices.userapi.controller;

import static com.mypractice.microservices.userapi.util.ObjectUtilMapper.map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mypractice.microservices.userapi.dto.UserDto;
import com.mypractice.microservices.userapi.model.UserCreationModel;
import com.mypractice.microservices.userapi.model.UserResponseModel;
import com.mypractice.microservices.userapi.service.SingUpService;
/**
 * nasru - Jun 20, 2020
 * UserController.java 
 */
@RestController
@RequestMapping("/users")
public class UserController {
	org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	private SingUpService singUpService;
	private Environment env;
	@Autowired
	public UserController(final Environment env, final SingUpService singUpService) {
		this.env = env;
		this.singUpService = singUpService;
	}

	@GetMapping("/user")
	public String returnMono() {
		return env.getProperty("user.username") +" User Api working "+ env.getProperty("local.server.port") +" secret key "+env.getProperty("token.secret");
	}
	@PostMapping("/signup")
	public UserResponseModel createUser(@RequestBody UserCreationModel userCreationModel) {
		logger.info("UserController.createUser() start ->{}", userCreationModel );
		UserDto userDto = map(userCreationModel, UserDto.class);
		userDto = singUpService.createUser(userDto);
		UserResponseModel response  =  map(userDto, UserResponseModel.class);
		response.setPort(env.getProperty("local.server.port"));
		logger.info("UserController.createUser() end ->{}", userCreationModel );
		return response; 
	}
}
