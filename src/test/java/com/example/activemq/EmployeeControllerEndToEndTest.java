package com.example.activemq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.activemq.controller.EmployeeController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
@Import(value = { JdbcConfiguration.class })
@TestMethodOrder(OrderAnnotation.class)
class EmployeeControllerEndToEndTest {	
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Sql(scripts = { "classpath:db/insert_employee.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	//@Sql(scripts = { "classpath:db/delete_employee.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Order(1)
	void testGetAllEmployees() throws Exception {
		
		this.mockMvc.perform(get("/api/v1/employees"))
        	.andDo(print())
			.andExpect(status().isOk());
	}
	
	//@Sql(scripts = { "classpath:db/delete_employee.sql" }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Order(2)
	void testCreateEmployee() throws Exception {
		
		this.mockMvc.perform(post("/api/v1/employees"))
        	.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.name").value("Alessio"));
	}

}
