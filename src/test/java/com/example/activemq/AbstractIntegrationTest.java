package com.example.activemq;

import java.time.Duration;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.PlatformTransactionManager;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.oracle.OracleContainer;
import org.testcontainers.utility.DockerImageName;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Testcontainers
public abstract class AbstractIntegrationTest {
	
	private static final DockerImageName ORACLE_IMAGE = DockerImageName.parse("gvenzl/oracle-free:slim-faststart");
    private static final String USERNAME = "EMPLOYEE_DB";
    
    
    @Container
	protected static final OracleContainer oracleContainer = new OracleContainer(ORACLE_IMAGE.asCompatibleSubstituteFor("gvenzl/oracle-free"))
            .withUsername(USERNAME)
            .withReuse(true)
            .withInitScript("init_employee_db.sql")
            .withStartupTimeout(Duration.ofMinutes(5L));
	
	@DynamicPropertySource
    private static void setupProperties(DynamicPropertyRegistry registry) {
    	log.info("URL: [{}]", oracleContainer.getJdbcUrl());
    	registry.add("spring.datasource.url", () -> oracleContainer.getJdbcUrl());
        registry.add("spring.datasource.username", () -> oracleContainer.getUsername());
        registry.add("spring.datasource.password", () -> oracleContainer.getPassword());
    }

}
