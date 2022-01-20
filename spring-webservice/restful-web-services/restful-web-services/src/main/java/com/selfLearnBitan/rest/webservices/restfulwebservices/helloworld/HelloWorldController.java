package com.selfLearnBitan.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
@RequestMapping(path = "/hello-world-controller")
public class HelloWorldController {
	
	@Autowired
	MessageSource messageSource;
	//GET
	//URI - /info
	//method - "the service is active"
	// @RequestMapping(method = RequestMethod.GET, path="/info") <-- generic way
	@GetMapping(path="/info") // <-- sleek way
	public String helloWorld() {
		return "The service is active";
	}
	
	//info-bean
	@GetMapping(path="/info-bean") // <-- sleek way
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("The service is active");
	}
	
	//info-bean
	@GetMapping(path="/info/path-variable/{name}") // <-- sleek way
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hi %s, the service is working great",name));
	}
	
	@GetMapping(path="/info-internation") // <-- sleek way
	public String helloWorldInternationalized(
			// @RequestHeader(name = "Accept-Language", required = false) Locale locale
			) {
		return messageSource.getMessage("good.morning.message", null,"Default Message" ,LocaleContextHolder.getLocale());
	}
	
	
}
