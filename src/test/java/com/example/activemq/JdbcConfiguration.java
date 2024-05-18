package com.example.activemq;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.oracle.OracleContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import jakarta.persistence.EntityManagerFactory;


@TestConfiguration
@ComponentScan(basePackages = { 
	"com.example.activemq.service" 
})
public class JdbcConfiguration {
	
	//private static final GenericContainer<?> oracleContainer;
	private static final OracleContainer oracleContainer;

	static {
		/*oracleContainer = new GenericContainer<>(DockerImageName.parse("gvenzl/oracle-free:slim-faststart")
				.asCompatibleSubstituteFor("gvenzl/oracle-free"))
			    .waitingFor(Wait.forLogMessage(".*DATABASE IS READY TO USE!.*\\s", 1))
			    .with;*/
		
		oracleContainer = new OracleContainer(
			    DockerImageName.parse("gvenzl/oracle-free:slim-faststart")
			            .asCompatibleSubstituteFor("gvenzl/oracle-free"))
					.waitingFor(Wait.forLogMessage(".*DATABASE IS READY TO USE!.*\\s", 1))
			        .withDatabaseName("EMPLOYEE_DB")
			        .withUsername("EMPLOYEE_USER")
			        .withPassword(("EMPLOYEE_PASSWORD"))
			        .withCopyFileToContainer(
			        		MountableFile.forHostPath("oracle-initscript.sql"), "init_employee_db.sql");
		
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