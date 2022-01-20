package com.bitan.microservices.currencyexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitan.microservices.currencyexchange.entity.CurrencyExchange;
import com.bitan.microservices.currencyexchange.repository.CurrencyConverterRepository;

@Service
public class CurrencyExchangeService{
	
	@Autowired
	private CurrencyConverterRepository ccr;
	
	public CurrencyExchange findByFromAndTo(String from, String to) {
		return ccr.findByFromAndTo(from, to);
	}
}
