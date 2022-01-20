package com.bitan.microservices.currencyexchange.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bitan.microservices.currencyexchange.entity.CurrencyExchange;

@Repository
public interface CurrencyConverterRepository extends MongoRepository<CurrencyExchange, String>{
	
	public CurrencyExchange findByFromAndTo(String from, String to);
}
