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
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

@SpringBootTest
@Testcontainers
public class MySQLTest {
	
	@Autowired
    private DataSource dataSource;
 
    @Container
    private static final GenericContainer<?> mySQLContainer = new GenericContainer<>("mysql:latest")
            .withEnv("MYSQL_ROOT_PASSWORD", "pass")
            .withEnv("MYSQL_DATABASE", "testcontainer")
            .withEnv("MYSQL_USER", "user")
            .withEnv("MYSQL_PASSWORD", "pass")
            .withExposedPorts(3306)
            .waitingFor(Wait.forLogMessage(".*mysqld: ready for connections.*", 2))
            //.withCopyFileToContainer(MountableFile.forClasspathResource("init.sql"), "/docker-entrypoint-initdb.d/schema.sql")
            ;
 
    @DynamicPropertySource
    private static void setupProperties(DynamicPropertyRegistry registry) {
        String url = "jdbc:mysql://localhost:" + mySQLContainer.getMappedPort(3306) + "/testcontainer";
        registry.add("spring.datasource.url", () -> url);
        registry.add("spring.datasource.username", () -> "user");
        registry.add("spring.datasource.password", () -> "pass");
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
