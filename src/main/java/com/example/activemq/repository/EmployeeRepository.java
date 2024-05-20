package com.example.activemq.repository;

import java.math.BigDecimal;

import com.example.activemq.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, BigDecimal> {

}
