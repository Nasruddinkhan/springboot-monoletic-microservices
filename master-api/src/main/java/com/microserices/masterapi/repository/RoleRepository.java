package com.microserices.masterapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.microserices.masterapi.documents.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

	Optional<Role> findByName(String name);

	List<Role> findByName(List<String> name);

}
