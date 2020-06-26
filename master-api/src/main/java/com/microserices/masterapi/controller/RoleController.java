package com.microserices.masterapi.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.microserices.masterapi.documents.Role;
import com.microserices.masterapi.repository.RoleRepository;

@RestController
@RequestMapping("/roles")
public  class RoleController {
	org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	private RoleRepository roleRepository;
	private Environment env;
	@Autowired
	public RoleController(final RoleRepository roleRepository, final Environment env) {
		super();
		this.roleRepository = roleRepository;
		this.env = env;
	}
	@GetMapping("/master")
	public String returnMono() {
		return "master api working " + env.getProperty("local.server.port");
	}
	@PostMapping("/role")
	public ResponseEntity<?>  addRole(@RequestBody Role role) {
		role = roleRepository.save(role);
		return ResponseEntity.created(
				ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}/roleId")
				.buildAndExpand(role.getId()).toUri()).build();
	}
	
	@GetMapping("/role/{id}/roleId")
	public EntityModel<Role> retrieveRole(@PathVariable String id) {
		Optional<Role> role = roleRepository.findById(id);
		if (!role.isPresent())
			throw new RuntimeException("id-" + id);
		EntityModel<Role> resource =  EntityModel.of(role.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAllRole());
		resource.add(linkTo.withRel("all-roles"));
		return resource;
	}
	@GetMapping("/role")
	public List<Role> findAllRole() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}
	
	@DeleteMapping("/role/{id}/roleId")
	public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
		Optional<Role> role = roleRepository.findById(id);
		if (!role.isPresent())
			throw new RuntimeException("id-" + id);
		roleRepository.delete(role.get());
	    return ResponseEntity.noContent().build();
	}
	@GetMapping("/role/{name}/name")
	public EntityModel<Role> retrieveRoleByname(@PathVariable String name) {
		Optional<Role> role = roleRepository.findByName(name);
		if (!role.isPresent())
			throw new RuntimeException("name-" + name);
		EntityModel<Role> resource =  EntityModel.of(role.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAllRole());
		resource.add(linkTo.withRel("all-roles"));
		return resource;
	}
	@PostMapping("/role/name")
	public List<String> retrieveRoleByname(@RequestBody List<String> name) {
		logger.info("callig retrieveRoleByname microservices");
		return roleRepository
							.findByName(name)
							.stream()
							.map(Role::getName)
							.collect(Collectors.toList());
	}
}
