package com.mypractice.microservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class WbsController {
	@GetMapping("/wbs")
	public Mono<String> returnMono() {
		return Mono.just("Wbs api working").log();
	}
}
