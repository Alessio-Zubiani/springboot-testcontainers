package com.example.activemq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.activemq.controller.MessageController;
import com.example.activemq.service.Message;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MessageController.class)
@ContextConfiguration(classes = { JmsConfiguration.class })
@TestInstance(Lifecycle.PER_CLASS)
public class MessageEndToEndTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	
	@Test
	void testSendMessage() throws Exception {
		
		this.mockMvc.perform(get("/api/v1/send"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value("Message sent successfully"));
	}
	
	@Test
	void testReceiveMessage() throws Exception {
		
		this.mockMvc.perform(get("/api/v1/receive"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.content").value("End2End message"));
	}
	
	@AfterAll
	void tearDown() {
		//clear all messages from queue
		
		Message m = (Message) this.jmsTemplate.receiveAndConvert("queue");
		System.out.println("Read message: " + m);
		while(m != null) {
			m = (Message) this.jmsTemplate.receiveAndConvert("queue");
			System.out.println("Read message: " + m);
		}
	}

}
