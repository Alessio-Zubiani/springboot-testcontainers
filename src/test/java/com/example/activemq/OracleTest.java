package com.example.activemq;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.oracle.OracleContainer;

@SpringBootTest
@Testcontainers
public class OracleTest {
	
	@Autowired
    private DataSource dataSource;
 
    /*@Container
    private static final GenericContainer<?> oracleContainer = new GenericContainer<>("gvenzl/oracle-free:slim-faststart")
            .withEnv("ORACLE_ROOT_PASSWORD", "pass")
            .withEnv("ORACLE_DATABASE", "testcontainer")
            .withEnv("ORACLE_USER", "user")
            .withEnv("ORACLE_PASSWORD", "pass")
            .withExposedPorts(1521)
            .waitingFor(Wait.forLogMessage(".*mysqld: ready for connections.*", 2))
            .withCopyFileToContainer(MountableFile.forClasspathResource("init.sql"), "/docker-entrypoint-initdb.d/schema.sql")
            ;*/
	
	@Container
	private static final OracleContainer oracleContainer = new OracleContainer("gvenzl/oracle-free:slim-faststart")
            .withDatabaseName("EMPLOYEE_DB")
            .withUsername("EMPLOYEE_USER")
            .withPassword("EMPLOYEE_PASSWORD")
            ;
 
    @DynamicPropertySource
    private static void setupProperties(DynamicPropertyRegistry registry) {
    	registry.add("spring.datasource.url", () -> oracleContainer.getJdbcUrl());
        registry.add("spring.datasource.username", () -> oracleContainer.getUsername());
        registry.add("spring.datasource.password", () -> oracleContainer.getPassword());
    }
 
    @Test
    void testTableExists() throws SQLException {
        try (Connection conn = dataSource.getConnection();
            ResultSet resultSet = conn.prepareStatement("SHOW TABLES").executeQuery();) {
            resultSet.next();
 
            String table = resultSet.getString(1);
            assertThat(table).isEqualTo("tests");
        }
    }

}
