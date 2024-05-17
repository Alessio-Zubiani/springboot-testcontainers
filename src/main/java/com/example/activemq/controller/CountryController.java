package com.example.activemq.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.activemq.service.Country;
import com.example.activemq.service.CountryService;

import org.oorsprong.websamples.FullCountryInfoAllCountriesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
public class CountryController {
	
	private final CountryService service;
	
	
	@GetMapping
	public ResponseEntity<List<Country>> sendMessage() {
		
		log.info("Sending message");
		FullCountryInfoAllCountriesResponse response = this.service.getAllCountriesInfo();
		
		List<Country> list = new ArrayList<>();
		response.getFullCountryInfoAllCountriesResult().getTCountryInfo().forEach(c -> {
			list.add(Country.builder()
					.name(c.getSName())
					.isoCode(c.getSISOCode())
					.capital(c.getSCapitalCity())
					.continent(c.getSContinentCode())
					.currencyCode(c.getSCurrencyISOCode())
					.phoneCode(c.getSPhoneCode())
					.build());
		});
		
		return ResponseEntity.ok()
				.body(list);
	}

}
