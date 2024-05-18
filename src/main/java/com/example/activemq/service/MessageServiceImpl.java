/*package com.example.activemq.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.JMSException;
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
		log.info("Message successfully sent");
	}

	@Override
	public Message receiveMessage() throws JMSException {
		
		log.info("Try receiving message");
		Message msg = (Message) this.jmsTemplate.receiveAndConvert("queue");
		log.info("Received message: [{}]", msg);
		
		return msg;
	}

}
*/