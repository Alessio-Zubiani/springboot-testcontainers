package com.example.activemq.repository;

import java.math.BigDecimal;

import com.example.activemq.service.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, BigDecimal> {

}
