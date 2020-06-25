/**
 * nasru - Jun 20, 2020
 * SingUpServiceImpl.java 
 */
package com.mypractice.microservices.userapi.serviceimpl;

import static com.mypractice.microservices.userapi.util.ObjectUtilMapper.map;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mypractice.microservices.userapi.config.MasterApiClient;
import com.mypractice.microservices.userapi.document.User;
import com.mypractice.microservices.userapi.dto.UserDto;
import com.mypractice.microservices.userapi.model.RoleResponseModel;
import com.mypractice.microservices.userapi.repository.UserRepository;
import com.mypractice.microservices.userapi.service.SingUpService;
import com.mypractice.microservices.userapi.service.UserSerivice;

/**
 * @author nasru
 *
 */
@Service("singUpService")
public class SingUpServiceImpl implements SingUpService, UserSerivice {
	private UserRepository userRepository;
	private MasterApiClient masterApi;
	private BCryptPasswordEncoder bcryptPasswordEncode;
    private Environment env;
    private RestTemplate restTemplate;
	@Autowired
	public SingUpServiceImpl(final UserRepository userRepository, 
			final RestTemplate restTemplate,
			final BCryptPasswordEncoder bcryptPasswordEncode,
			final Environment env,
			final MasterApiClient masterApi) {
		super();
		this.masterApi = masterApi;
		this.userRepository = userRepository;
		this.bcryptPasswordEncode = bcryptPasswordEncode;
		this.env = env;
		this.restTemplate= restTemplate;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = map(userDto, User.class);
		user.setRoles(getRoles(userDto.getRoles()));
		user.setPassword(bcryptPasswordEncode.encode(userDto.getPassword()));
		user = userRepository.save(user);
		return map(user, UserDto.class);
	}

	/**
	 * @return
	 */
	private Set<RoleResponseModel> getRoles(Set<RoleResponseModel> roles) {
		// TODO Auto-generated method stub
		
		return roles.stream()
				.map(s -> masterApi.getRoles(s.getName()))
				.collect(Collectors.toSet());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UsernameNotFoundException(email);
		return map(user, UserDto.class);
	}
}
