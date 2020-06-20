/**
 * nasru - Jun 20, 2020
 * RoleRepository.java 
 */
package com.mypractice.microservices.userapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mypractice.microservices.userapi.document.Role;
import com.mypractice.microservices.userapi.enmus.UserRole;

/**
 * @author nasru
 *
 */
@Repository
public interface RoleRepository extends MongoRepository<Role, String>{
	Role findByName(UserRole name);
}
