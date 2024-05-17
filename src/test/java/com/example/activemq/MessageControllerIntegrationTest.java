package com.example.activemq;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.activemq.controller.MessageController;
import com.example.activemq.service.Message;
import com.example.activemq.service.MessageService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MessageController.class)
public class MessageControllerIntegrationTest {
	
	@MockBean
	private MessageService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void testSendMessage() throws Exception {
		
		Mockito.doNothing().when(this.service).sendMessage(Mockito.any(Message.class));
		this.mockMvc.perform(get("/api/v1/messages/send"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value("Message sent successfully"));
		
		Mockito.verify(this.service, times(1)).sendMessage(Mockito.any(Message.class));
	}

}
