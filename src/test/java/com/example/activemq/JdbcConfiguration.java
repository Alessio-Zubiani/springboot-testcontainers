package com.example.activemq;

import java.time.Duration;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.oracle.OracleContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import jakarta.persistence.EntityManagerFactory;


@SuppressWarnings("resource")
@TestConfiguration
@ComponentScan(basePackages = { 
	"com.example.activemq.service" 
})
public class JdbcConfiguration {
	
	private static final OracleContainer oracleContainer;

	static {
		String regex = ".*(\"message\":\\s?\"started\".*|] started\n$)";
		
		oracleContainer = new OracleContainer(
			    DockerImageName.parse("gvenzl/oracle-free:slim-faststart")
			            .asCompatibleSubstituteFor("gvenzl/oracle-free"))
			        .withDatabaseName("EMPLOYEE_DB")
			        .withUsername("EMPLOYEE_USER")
			        .withPassword(("EMPLOYEE_PASSWORD"))
			        .withCopyFileToContainer(
			        		MountableFile.forHostPath("oracle-initscript.sql"), "init_employee_db.sql");
		
		oracleContainer.setWaitStrategy((new LogMessageWaitStrategy())
    		    .withRegEx(regex)
    		    .withStartupTimeout(Duration.ofMinutes(5)));
		oracleContainer.start();
	}
	
	@Bean
    public DataSource getDataSource() {
		
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("oracle.jdbc.OracleDriver");
        dataSourceBuilder.url(oracleContainer.getJdbcUrl());
        dataSourceBuilder.username(oracleContainer.getUsername());
        dataSourceBuilder.password(oracleContainer.getPassword());
        
        return dataSourceBuilder.build();
    }
	
	@Bean
	public LocalContainerEntityManagerFactoryBean bicompEntityManager(EntityManagerFactoryBuilder builder, DataSource dataSource) {
		
		return builder
				.dataSource(dataSource)
				.packages("com.example.activemq.service")
				.build();
	}
	
	@Bean
	public PlatformTransactionManager bicompTransactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}