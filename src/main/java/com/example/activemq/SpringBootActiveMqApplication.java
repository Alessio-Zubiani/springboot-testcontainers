package com.example.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootActiveMqApplication {	

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActiveMqApplication.class, args);
	}

}
