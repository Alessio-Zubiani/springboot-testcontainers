package com.example.activemq;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.oracle.OracleContainer;
import org.testcontainers.utility.DockerImageName;

import lombok.extern.slf4j.Slf4j;

@Slf4j

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
            //.waitingFor(Wait.forLogMessage(".*mysqld: ready for connections.*", 2))
            //.withCopyFileToContainer(MountableFile.forClasspathResource("init.sql"), "/docker-entrypoint-initdb.d/schema.sql")
            ;
    
    @DynamicPropertySource
    private static void setupProperties(DynamicPropertyRegistry registry) {
    	//jdbc:tc:oracle:thin:localhost:1521/testcontainer
    	String url = "jdbc:tc:" + oracleContainer.getDockerImageName() + "://testcontainer";
    	log.info("URL: [{}]", url);
    	registry.add("spring.datasource.url", () -> url);
        registry.add("spring.datasource.username", () -> "user");
        registry.add("spring.datasource.password", () -> "pass");
    }*/
	
	@Container
	private static final OracleContainer oracleContainer = new OracleContainer(
				DockerImageName.parse("gvenzl/oracle-free:slim-faststart")
					.asCompatibleSubstituteFor("gvenzl/oracle-free"))
			.withDatabaseName("EMPLOYEE_DB")
            .withUsername("EMPLOYEE_USER")
            .withPassword("EMPLOYEE_PASSWORD");
	
	@DynamicPropertySource
    private static void setupProperties(DynamicPropertyRegistry registry) {
    	log.info("URL: [{}]", oracleContainer.getJdbcUrl());
    	registry.add("spring.datasource.url", () -> oracleContainer.getJdbcUrl());
        registry.add("spring.datasource.username", () -> oracleContainer.getUsername());
        registry.add("spring.datasource.password", () -> oracleContainer.getPassword());
    }
 
    @Test
    void testTableExists() throws SQLException {
        try (Connection conn = this.dataSource.getConnection();
            ResultSet resultSet = conn.prepareStatement("SHOW TABLES").executeQuery();) {
            resultSet.next();
 
            String table = resultSet.getString(1);
            assertThat(table).isEqualTo("tests");
        }
    }
    
    /*@Bean
    public DataSource dataSource() {
		
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("oracle.jdbc.OracleDriver");
        dataSourceBuilder.url(CONTAINER.getJdbcUrl());
        dataSourceBuilder.username(CONTAINER.getUsername());
        dataSourceBuilder.password(CONTAINER.getPassword());
        
        return dataSourceBuilder.build();
    }
	
	@Bean
	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
		return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManager(EntityManagerFactoryBuilder entityManagerFactoryBuilder, DataSource dataSource) {
		
		return entityManagerFactoryBuilder
				.dataSource(dataSource)
				.packages("com.example.activemq.service")
				.build();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManager) {
		return new JpaTransactionManager(entityManager);
	}*/

}
