package com.bitan.microservices.currencyexchange.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitan.microservices.currencyexchange.entity.CurrencyExchange;
import com.bitan.microservices.currencyexchange.service.CurrencyExchangeService;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

	@Autowired
	private Environment env;
	
	@Autowired
	private CurrencyExchangeService cex;
	
	private Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@GetMapping("/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to){
		
		log.info("retrieveExchangeValue called with from {} to {}",from,to);
		to=to.toUpperCase();
		from=from.toUpperCase();
		CurrencyExchange ce =  cex.findByFromAndTo(from, to);
		if(ce==null)
			throw new RuntimeException("No data found");
		ce.setEnvironment(env.getProperty("local.server.port"));
		return ce;
	}
	
}
