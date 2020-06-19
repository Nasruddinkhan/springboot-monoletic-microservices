package com.microserices.masterapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class MasterController {
	@GetMapping("/master")
	public Mono<String> returnMono() {
		return Mono.just("master api working").log();
	}
}
