package com.example.activemq;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitAllStrategy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.oracle.OracleContainer;
import org.testcontainers.utility.DockerImageName;


@TestConfiguration
@ComponentScan(basePackages = { 
	"com.example.activemq.service" 
})
public class JdbcConfiguration {
	
	@Container
    @ServiceConnection
    static OracleContainer oracleContainer = new OracleContainer(
            DockerImageName.parse("gvenzl/oracle-free:slim-faststart")
                    .asCompatibleSubstituteFor("gvenzl/oracle-free"))
    		.withDatabaseName("EMPLOYEE_DB")
	        .withUsername("EMPLOYEE_USER")
	        .withPassword("EMPLOYEE_PASSWORD");
	
	//private static final GenericContainer<?> oracleContainer;
	/*public static OracleContainer oracleContainer;

	static {
		oracleContainer = new OracleContainer("gvenzl/oracle-free:slim-faststart")
				//.waitingFor(Wait.forLogMessage(".*DATABASE IS READY TO USE!.*\\s", 1));
				//.waitingFor(Wait.forLogMessage(".*Ready to accept connections.*\\\\n\"", 1));
			.waitingFor(
					new WaitAllStrategy()
			            .withStrategy(Wait.forLogMessage(".*database system is ready to accept connections.*\\s", 2))
			            .withStrategy(Wait.forListeningPort())
			    );
				
		oracleContainer.start();
	}
		oracleContainer = new GenericContainer<>(DockerImageName.parse("gvenzl/oracle-free:slim-faststart")
				.asCompatibleSubstituteFor("gvenzl/oracle-free"))
			    .waitingFor(Wait.forLogMessage(".*DATABASE IS READY TO USE!.*\\s", 1))
			    .with;
		
		oracleContainer = new OracleContainer(
			    DockerImageName.parse("gvenzl/oracle-free:slim-faststart")
			            .asCompatibleSubstituteFor("gvenzl/oracle-free"))
			        .withDatabaseName("EMPLOYEE_DB")
			        .withUsername("EMPLOYEE_USER")
			        .withPassword("EMPLOYEE_PASSWORD")
			        .withCopyFileToContainer(
			        		MountableFile.forHostPath("oracle-initscript.sql"), "init_employee_db.sql");
		
		oracleContainer.start();
		oracleContainer.waitingFor(Wait.forLogMessage(".*DATABASE IS READY TO USE!.*\\s", 1));
	}
	
	@DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", oracleContainer::getJdbcUrl);
        registry.add("spring.datasource.username", oracleContainer::getUsername);
        registry.add("spring.datasource.password", oracleContainer::getPassword);
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
	}*/

}