package com.microserices.integration.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
@RestController
public class IntegrationAppController {
	@GetMapping("/integrationapi")
	public Mono<String> returnMono() {
		return Mono.just("integrationapi api working").log();
	}
}
