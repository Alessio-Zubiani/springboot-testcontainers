package com.example.activemq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.activemq.controller.EmployeeController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(EmployeeController.class)
@ContextConfiguration(classes = { JdbcConfiguration.class })
class EmployeeControllerEndToEndTest {	
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Sql(scripts = { "classpath:db/insert_employee.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts = { "classpath:db/delete_employee.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	void testGetAllEmployees() throws Exception {
		
		this.mockMvc.perform(get("/api/v1/employees").accept(MediaType.APPLICATION_JSON))
        	.andDo(print())
			.andExpect(status().isOk());
	}

}
