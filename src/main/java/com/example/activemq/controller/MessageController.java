package com.example.activemq.controller;

import java.util.Date;

import com.example.activemq.service.Message;
import com.example.activemq.service.MessageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.jms.JMSException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
	
	private final MessageService service;
	
	@GetMapping("send")
	public ResponseEntity<String> sendMessage() {
		
		log.info("Sending message");
		this.service.sendMessage(Message.builder()
				.id(100L)
				.content("End2End message")
				.date(new Date())
				.build());
		
		return ResponseEntity.ok()
				.body("Message sent successfully");
	}
	
	@GetMapping("receive")
	public ResponseEntity<Message> receiveMessage() throws JMSException {
		
		Message message = this.service.receiveMessage();
		log.info("Received message: [{}]", message);
		
		return ResponseEntity.ok()
				.body(message);
	}

}
