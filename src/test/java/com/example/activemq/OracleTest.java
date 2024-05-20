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
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class OracleTest {
	
	@Autowired
    private DataSource dataSource;
 
    @Container
    private static final GenericContainer<?> oracleContainer = new GenericContainer<>("gvenzl/oracle-free:slim-faststart")
            /*.withEnv("ORACLE_ROOT_PASSWORD", "pass")*/
            .withEnv("ORACLE_DATABASE", "testcontainer")
            .withEnv("ORACLE_USER", "user")
            .withEnv("ORACLE_PASSWORD", "pass")
            .withExposedPorts(1521)
            //.waitingFor(Wait.forLogMessage(".*mysqld: ready for connections.*", 2))
            //.withCopyFileToContainer(MountableFile.forClasspathResource("init.sql"), "/docker-entrypoint-initdb.d/schema.sql")
            ;
 
    @DynamicPropertySource
    private static void setupProperties(DynamicPropertyRegistry registry) {
    	//jdbc:oracle:thin:@oratest.popso.it:1521/oltptestsrv.popso.it
        String url = "jdbc:oracle:thin:@localhost:" + oracleContainer.getMappedPort(1521) + "/testcontainer";
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
