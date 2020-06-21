/**
 * nasru - Jun 20, 2020
 * SingUpServiceImpl.java 
 */
package com.mypractice.microservices.userapi.serviceimpl;

import static com.mypractice.microservices.userapi.util.ObjectUtilMapper.map;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mypractice.microservices.userapi.document.User;
import com.mypractice.microservices.userapi.dto.UserDto;
import com.mypractice.microservices.userapi.model.RoleResponseModel;
import com.mypractice.microservices.userapi.repository.UserRepository;
import com.mypractice.microservices.userapi.service.SingUpService;

/**
 * @author nasru
 *
 */
@Service("singUpService")
public class SingUpServiceImpl implements SingUpService {
	private UserRepository userRepository;
	private RestTemplate restTemplate;
	@Autowired
	public SingUpServiceImpl(final UserRepository userRepository, final RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
		this.userRepository = userRepository;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user =  map(userDto, User.class);
		user.setRoles(getRoles(userDto.getRoles()));
		user = userRepository.save(user);
		System.out.println(user);
		return map(user, UserDto.class);
	}

	/**
	 * @return
	 */
	private Set<RoleResponseModel> getRoles(Set<String> roles) {
		// TODO Auto-generated method stub
		return   roles.stream()
				.map(s ->restTemplate.getForObject("http://localhost:8765/master-ws/roles/role/{name}/name", RoleResponseModel.class, s))
				.collect(Collectors.toSet());
	}
}
