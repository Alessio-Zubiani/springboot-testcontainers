package com.example.activemq.service;

import jakarta.jms.JMSException;

public interface MessageService {
	
	void sendMessage(Message message);
	
	Message receiveMessage() throws JMSException;

}
