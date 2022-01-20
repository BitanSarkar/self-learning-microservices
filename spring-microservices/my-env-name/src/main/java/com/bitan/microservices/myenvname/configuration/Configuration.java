package com.bitan.microservices.myenvname.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("my-env-name")
public class Configuration {
	private String name;
}
