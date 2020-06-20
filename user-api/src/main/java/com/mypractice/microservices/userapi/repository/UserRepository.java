/**
 * nasru - Jun 20, 2020
 * UserRepository.java 
 */
package com.mypractice.microservices.userapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mypractice.microservices.userapi.document.User;

/**
 * @author nasru
 *
 */
@Repository
public interface  UserRepository extends MongoRepository<User, String> {
	User findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
