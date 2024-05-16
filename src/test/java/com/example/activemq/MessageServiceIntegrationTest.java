package com.example.activemq;

import java.util.Date;

import com.example.activemq.service.Message;
import com.example.activemq.service.MessageService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.Import;

import jakarta.jms.JMSException;


@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
@Import(JmsConfiguration.class)
class MessageServiceIntegrationTest {
	
	@Autowired
	private MessageService messageService;
	
	
	@Test
    void testSendMessage() throws JMSException {
		Message message = Message.builder()
				.id(1000L)
				.content("Integration test message")
				.date(new Date())
				.build();

        // Sending a text message to the queue
		Assertions.assertDoesNotThrow(() -> {
			this.messageService.sendMessage(message);
		});

        /*
        // Receiving the message from the queue
        TextMessage receivedMessage = (TextMessage) this.jmsTemplate.receive("test.queue");
        System.out.println("Received message: [" + receivedMessage.getText() + "]");

        assertThat(dummyPayload).isEqualTo(receivedMessage.getText());*/
    }

}
