package com.example.activemq.service;

import java.util.List;

import com.example.activemq.entity.Employee;
import com.example.activemq.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	
	@Override
	public Employee createEmployee() {
		
		Employee e = Employee.builder()
				.name("Alessio")
				.surname("Zubiani")
				.department("IT")
				.build();
		
		return this.employeeRepository.save(e);
	}

	@Override
	public List<Employee> getEmployees() {
		
		List<Employee> list = this.employeeRepository.findAll();
		log.info("List size: [{}]", list.size());
		
		return list;
	}

}
