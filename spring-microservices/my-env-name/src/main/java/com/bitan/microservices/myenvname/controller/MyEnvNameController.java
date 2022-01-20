package com.bitan.microservices.myenvname.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitan.microservices.myenvname.configuration.Configuration;
import com.bitan.microservices.myenvname.entity.EnvName;

@RestController
public class MyEnvNameController {
	@Autowired
	private Configuration config;
	
	@GetMapping("/my-name")
	public EnvName retrieveLimits() {
		return new EnvName(config.getName());
	}

}
