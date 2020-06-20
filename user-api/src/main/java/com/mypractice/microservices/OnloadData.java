/**
 * nasru - Jun 21, 2020
 * OnloadData.java 
 */
package com.mypractice.microservices;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author nasru
 *
 */
@Component
public class OnloadData implements CommandLineRunner {
//@Autowired
//private RoleRepository repo;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("called");
	/*Role role = new Role();
	role.setId(null);
	role.setName(UserRole.ROLE_ADMIN);
	Role role1 = new Role();
	role1.setId(null);
	role1.setName(UserRole.ROLE_USER);
	repo.save(role1);
	repo.save(role);*/
	}

}
