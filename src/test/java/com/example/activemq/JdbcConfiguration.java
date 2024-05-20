package com.example.activemq;

import java.time.Duration;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.oracle.OracleContainer;
import org.testcontainers.utility.DockerImageName;

import jakarta.persistence.EntityManagerFactory;


@SuppressWarnings("resource")
@TestConfiguration
@ComponentScan(basePackages = { 
	"com.example.activemq.service", "com.example.activemq.repository" 
})
public class JdbcConfiguration {
	
	private static final String DOCKER_IMAGE_NAME = "gvenzl/oracle-free:slim-faststart";
    private static final String ORACLE_USER = "user";
    private static final String ORACLE_PASSWORD = "pass";
    private static final String ORACLE_DATABASE = "db";

    protected static final OracleContainer CONTAINER;

    static {
        DockerImageName imageName = DockerImageName.parse(DOCKER_IMAGE_NAME).asCompatibleSubstituteFor("gvenzl/oracle-free");
        
        CONTAINER = (OracleContainer) new OracleContainer(imageName)
                .withDatabaseName(ORACLE_DATABASE)
                .withUsername(ORACLE_USER)
                .withPassword(ORACLE_PASSWORD)
                .withEnv("ORACLE_DATABASE", ORACLE_DATABASE)
                .withEnv("ORACLE_USER", ORACLE_USER)
                .withEnv("ORACLE_PASSWORD", ORACLE_PASSWORD);

        CONTAINER.start();
        /*CONTAINER.setWaitStrategy(Wait.defaultWaitStrategy()
                .withStartupTimeout(Duration.ofSeconds(300)));*/
        CONTAINER.waitingFor(
        	Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(300L))
        );
    }
	
	/*private static final GenericContainer<?> oracleContainer;

	static {
		oracleContainer = new GenericContainer<>("gvenzl/oracle-free:slim-faststart")
				.withEnv(null, null)
				.withDatabaseName("EMPLOYEE_DB")
		        .withUsername("EMPLOYEE_USER")
		        .withPassword("EMPLOYEE_PASSWORD")
				.withExposedPorts(1521, 5500);
		
		System.out.println();
		
		oracleContainer.start();
	}
	
	/*public static OracleContainer oracleContainer;

	static {
		oracleContainer = new OracleContainer(
			    DockerImageName.parse("gvenzl/oracle-free:slim-faststart")
			            .asCompatibleSubstituteFor("gvenzl/oracle-free"))
			        .withDatabaseName("EMPLOYEE_DB")
			        .withUsername("EMPLOYEE_USER")
			        .withPassword("EMPLOYEE_PASSWORD")
			        .withCopyFileToContainer(
			        		MountableFile.forHostPath("oracle-initscript.sql"), "init_employee_db.sql");
		
		oracleContainer.start();
	}
	
	oracleContainer = new GenericContainer<>(DockerImageName.parse("gvenzl/oracle-free:slim-faststart")
				.asCompatibleSubstituteFor("gvenzl/oracle-free"))
			    .waitingFor(Wait.forLogMessage(".*DATABASE IS READY TO USE!.*\\s", 1))
			    .with;
		
		
		oracleContainer.waitingFor(Wait.forLogMessage(".*DATABASE IS READY TO USE!.*\\s", 1));
	}
	
	@DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", oracleContainer.getBoundPortNumbers() ::getJdbcUrl);
        registry.add("spring.datasource.username", oracleContainer::getUsername);
        registry.add("spring.datasource.password", oracleContainer::getPassword);
    }*/
	
	@Bean
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
	}

}