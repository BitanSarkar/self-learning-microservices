package com.bitan.microservices.currencyexchange.entity;

import java.math.BigDecimal;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "currency-exchange-info")
public class CurrencyExchange {
	
	@Id @JsonIgnore
	private String id;
	
	private String from;
	private String to;
	private BigDecimal conversionMultiple;
	private String environment;
}
