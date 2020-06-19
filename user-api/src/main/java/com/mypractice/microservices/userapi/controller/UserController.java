package com.mypractice.microservices.userapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
	@Autowired
	private Environment env;
	@GetMapping("/flux")
	public Flux<Integer> returnFlux() {
		return Flux.just(1, 2, 3, 4, 5, 6).log();
	}
	@GetMapping("/user")
	public Mono<String> returnMono() {
		return Mono.just(env.getProperty("user.name") +"User Api working "+ env.getProperty("local.server.port")).log();
	}
}
