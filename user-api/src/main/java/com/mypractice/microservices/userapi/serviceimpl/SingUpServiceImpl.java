/**
 * nasru - Jun 20, 2020
 * SingUpServiceImpl.java 
 */
package com.mypractice.microservices.userapi.serviceimpl;

import static com.mypractice.microservices.userapi.util.ObjectUtilMapper.map;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypractice.microservices.userapi.document.Role;
import com.mypractice.microservices.userapi.document.User;
import com.mypractice.microservices.userapi.dto.UserDto;
import com.mypractice.microservices.userapi.enmus.UserRole;
import com.mypractice.microservices.userapi.repository.RoleRepository;
import com.mypractice.microservices.userapi.repository.UserRepository;
import com.mypractice.microservices.userapi.service.SingUpService;

/**
 * @author nasru
 *
 */
@Service("singUpService")
public class SingUpServiceImpl implements SingUpService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	@Autowired
	public SingUpServiceImpl(final UserRepository userRepository,final RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user =  map(userDto, User.class);
		Set<String> strRoles = userDto.getRoles();
		Set<Role> roles = new HashSet<>();
		strRoles.forEach(role ->{ 
			switch (role) {
			case "admin":
				roles.add(roleRepository.findByName(UserRole.ROLE_ADMIN));
				break;
			case "user":
				roles.add(roleRepository.findByName(UserRole.ROLE_ADMIN));
				break;
			default:
				break;
			}
		});
		user.setRoles(roles);
		user = userRepository.save(user);
		System.out.println(user);
		return map(user, UserDto.class);
	}

}
