package com.example.activemq;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

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

//@SpringBootTest
@Testcontainers
public class OracleTest {
	
	@Autowired
    private DataSource dataSource;
 
    /*@Container
    private static final GenericContainer<?> oracleContainer = new GenericContainer<>("gvenzl/oracle-free:slim-faststart")
            .withEnv("ORACLE_DATABASE", "EMPLOYEE_DB")
            .withEnv("ORACLE_USER", "user")
            .withEnv("ORACLE_PASSWORD", "pass")
            .withExposedPorts(1521)
            //.waitingFor(Wait.forLogMessage(".*mysqld: ready for connections.*", 2))
            //.withCopyFileToContainer(MountableFile.forClasspathResource("init.sql"), "/docker-entrypoint-initdb.d/schema.sql")
            ;
    
    @DynamicPropertySource
    private static void setupProperties(DynamicPropertyRegistry registry) {
    	//jdbc:oracle:thin:@localhost:32802/EMPLOYEE_DB
    	String url = "jdbc:oracle:thin:@" + oracleContainer.getHost() + ":" + oracleContainer.getMappedPort(1521) + "/EMPLOYEE_DB";
    	log.info("URL: [{}]", url);
    	registry.add("spring.datasource.url", () -> url);
        registry.add("spring.datasource.username", () -> "user");
        registry.add("spring.datasource.password", () -> "pass");
    }*/
	
	@Container
	private static final OracleContainer oracleContainer = new OracleContainer(
				DockerImageName.parse("gvenzl/oracle-free:slim-faststart")
					.asCompatibleSubstituteFor("gvenzl/oracle-free"))
            .withUsername("EMPLOYEE_DB")
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
 
    @Test
    void testTableExists() throws SQLException {
        try (Connection conn = this.dataSource.getConnection();
            ResultSet resultSet = conn.prepareStatement("SELECT * FROM all_tables ORDER BY table_name").executeQuery();
        ) {
        	while(resultSet.next()) {
        		String table = resultSet.getString(1);
                log.info("Table: [{}]", table);
        	}
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
