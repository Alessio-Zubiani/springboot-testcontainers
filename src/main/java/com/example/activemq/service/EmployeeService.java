package com.example.activemq.service;

import java.util.List;

import com.example.activemq.entity.Employee;

public interface EmployeeService {
	
	Employee createEmployee();
	
	List<Employee> getEmployees();

}
