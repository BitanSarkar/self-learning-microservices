package com.bitan.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitan.microservices.limitsservice.configuration.Configuration;
import com.bitan.microservices.limitsservice.entity.Limits;

@RestController
public class LimitsController {
	
	@Autowired
	private Configuration config;
	
	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return new Limits(config.getMinimum(),config.getMaximum());
		// return new Limits(1,100);
	}
}
