package com.selfLearnBitan.rest.webservices.restfulwebservices.filtering;

import java.util.Date;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	// Suppose I don't want to show dob, I have to filter out dob in the response
	// Just write JsonIgnore in Entity ---> static filtering
	@GetMapping("/user-controller/filtering")
	public User2 retrieveUser2Filter() {
		return new User2("Joker", new Date());
	}
	
	@GetMapping("/user-controller/filtering-dynamic")
	public MappingJacksonValue retrieveUser2FilterOne() {
		User2 user = new User2("Joker", new Date());
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name");
		FilterProvider filters = new SimpleFilterProvider().addFilter("User2Filter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		mapping.setFilters(filters);
		return mapping;
	}
}
