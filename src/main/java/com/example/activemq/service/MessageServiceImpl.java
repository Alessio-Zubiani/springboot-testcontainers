package com.example.activemq.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

	private final JmsTemplate jmsTemplate;
	
	
	@Override
	public void sendMessage(Message message) {
		
		log.info("Sending message: [{}]", message);
		this.jmsTemplate.convertAndSend("queue", message);
		log.info("Message sent");
	}

}
