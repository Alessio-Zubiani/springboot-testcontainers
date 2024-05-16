package com.example.activemq;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;

import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
@Import(JmsConfiguration.class)
class MessageSenderReceiverIntegrationTest {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	
	@Test
    void testSendMessage() throws JMSException {
        String dummyPayload = "Dummy payload";

        // Sending a text message to the queue
        this.jmsTemplate.convertAndSend("test.queue", dummyPayload);

        // Receiving the message from the queue
        TextMessage receivedMessage = (TextMessage) this.jmsTemplate.receive("test.queue");
        System.out.println("Received message: " + receivedMessage);

        assertThat(dummyPayload).isEqualTo(receivedMessage.getText());
    }

}
