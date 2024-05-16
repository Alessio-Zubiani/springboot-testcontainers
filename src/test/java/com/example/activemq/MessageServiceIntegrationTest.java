package com.example.activemq;

import java.util.Date;

import com.example.activemq.service.Message;
import com.example.activemq.service.MessageService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JmsConfiguration.class })
class MessageServiceIntegrationTest {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	
	@Test
    void testSendMessage() throws JMSException {
		Message message = Message.builder()
				.id(1000L)
				.content("Integration test message")
				.date(new Date())
				.build();

        // Sending a text message to the queue
		/*Assertions.assertDoesNotThrow(() -> {
			this.messageService.sendMessage(message);
		});*/

		jakarta.jms.Message m;
		while((m = this.jmsTemplate.receive("queue")) != null) {
			System.out.println("Received message: [" + ((TextMessage) m).getText() + "]");
		}
        /*// Receiving the message from the queue
        TextMessage receivedMessage = (TextMessage) this.jmsTemplate.receive("queue");
        System.out.println("Received message: [" + receivedMessage.getText() + "]");*/

        //assertThat(receivedMessage.getText()).isEqualTo(receivedMessage.getText());
    }

}
