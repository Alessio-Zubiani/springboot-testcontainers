package com.example.activemq.service;

import java.io.StringWriter;

import com.example.activemq.ws.client.WebServiceClient;

import org.oorsprong.websamples.FullCountryInfoAllCountries;
import org.oorsprong.websamples.FullCountryInfoAllCountriesResponse;
import org.oorsprong.websamples.ObjectFactory;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.WebServiceClientException;

import jakarta.xml.bind.JAXB;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
	
	private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

	private final WebServiceClient client;
	
	
	@Override
	public FullCountryInfoAllCountriesResponse getAllCountriesInfo() {
		
		try {
			FullCountryInfoAllCountries request = OBJECT_FACTORY.createFullCountryInfoAllCountries();
			
			FullCountryInfoAllCountriesResponse response = (FullCountryInfoAllCountriesResponse) 
					this.client.callWebService("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso", request);
			
			log.info("Received response: [{}]", this.xmlToString(response));
			return response;
		}
		catch (WebServiceClientException e) {
			log.error("Error: [{}]", e.getMessage());
			throw e;
		}
	}
	
	private String xmlToString(Object o) {
		
		StringWriter stringWriter = new StringWriter();
		JAXB.marshal(o, stringWriter);
		
		return stringWriter.toString();
	}

}
