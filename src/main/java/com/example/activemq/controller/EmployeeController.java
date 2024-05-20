package com.example.activemq.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.activemq.entity.Employee;
import com.example.activemq.service.EmployeeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

	/*private final EmployeeService service;
	
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		
		List<Employee> list = this.service.getEmployees();
		
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee() {
		
		Employee e = this.service.createEmployee();
		
		return ResponseEntity.ok().body(e);
	}*/
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		
		//List<Employee> list = this.service.getEmployees();
		List<Employee> list = new ArrayList<>();
		
		return ResponseEntity.ok().body(list);
	}
	
}
