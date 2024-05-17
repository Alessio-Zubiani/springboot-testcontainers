package com.example.activemq.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
	
	private String name;
	private String isoCode;
	private String capital;
	private String continent;
	private String currencyCode;
	private String phoneCode;

}
