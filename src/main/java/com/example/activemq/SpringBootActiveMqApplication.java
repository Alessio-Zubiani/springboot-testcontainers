package com.example.activemq;

import java.util.Date;

import com.example.activemq.service.Message;
import com.example.activemq.service.MessageService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootActiveMqApplication implements CommandLineRunner {
	
	private final MessageService messageService;
	

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActiveMqApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		this.messageService.sendMessage(Message.builder()
				.id(100L)
				.content("Test message")
				.date(new Date())
				.build());
	}

}
