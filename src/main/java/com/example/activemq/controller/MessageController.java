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

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageController {
	
	private final MessageService service;
	
	@GetMapping("send")
	public ResponseEntity<String> sendMessage() {
		
		this.service.sendMessage(Message.builder()
				.id(100L)
				.content("Test message")
				.date(new Date())
				.build());
		
		return ResponseEntity.ok()
				.body("Message sent successfully");
	}
	
	@GetMapping("receive")
	public ResponseEntity<Message> receiveMessage() throws JMSException {
		
		Message message = this.service.receiveMessage();
		
		return ResponseEntity.ok()
				.body(message);
	}

}
