/*package com.example.activemq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.activemq.controller.EmployeeController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@WebMvcTest(EmployeeController.class)
@ContextConfiguration(classes = { JdbcConfiguration.class })
class EmployeeControllerEndToEndTest {	
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void testGetAllEmployees() throws Exception {
		
		this.mockMvc.perform(get("/api/v1/employees"))
			.andExpect(status().isOk());
	}

}
*/