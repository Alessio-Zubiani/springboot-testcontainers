package com.example.activemq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.activemq.controller.CountryController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CountryController.class)
@ContextConfiguration(classes = { JmsConfiguration.class })
public class CountryControllerEndToEndTest {	
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void testSendMessage() throws Exception {
		
		this.mockMvc.perform(get("/api/v1/countries"))
			.andExpect(status().isOk());
	}

}
