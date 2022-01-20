package com.bitan.microservices.currencyconversion.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bitan.microservices.currencyconversion.entity.CurrencyConversion;
import com.bitan.microservices.currencyconversion.proxy.CurrencyExchangeProxy;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/from/{from}/to/{to}/quantity/{qty}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal qty) {
		to=to.toUpperCase();
		from=from.toUpperCase();
		HashMap<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
				.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConversion.class, uriVariables );
		CurrencyConversion currencyConversion = responseEntity.getBody();
		// return currencyConversion;
		return currencyConversion.builder()
				.from(from)
				.to(to)
				.conversionMultiple(currencyConversion.getConversionMultiple())
				.environment(currencyConversion.getEnvironment().concat(" in rest template"))
				.quantity(qty)
				.totalCalculatedAmount(qty.multiply(currencyConversion.getConversionMultiple()))
				.build();
	}
	
	@GetMapping("feign/from/{from}/to/{to}/quantity/{qty}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal qty) {
		to=to.toUpperCase();
		from=from.toUpperCase();
		
		CurrencyConversion currencyConversion =proxy.retrieveExchangeValue(from, to);
		// return currencyConversion;
		return currencyConversion.builder()
				.from(from)
				.to(to)
				.conversionMultiple(currencyConversion.getConversionMultiple())
				.environment(currencyConversion.getEnvironment().concat(" in feign"))
				.quantity(qty)
				.totalCalculatedAmount(qty.multiply(currencyConversion.getConversionMultiple()))
				.build();
	}
}
