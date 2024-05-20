package com.example.activemq;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = { "com.example.activemq.repository" })
@ComponentScan(basePackages = { 
	"com.example.activemq.service" 
})
public class JdbcConfiguration extends AbstractIntegrationTest {
	
	@Bean
    public DataSource dataSource() {
		
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("oracle.jdbc.OracleDriver");
        dataSourceBuilder.url(oracleContainer.getJdbcUrl());
        dataSourceBuilder.username(oracleContainer.getUsername());
        dataSourceBuilder.password(oracleContainer.getPassword());
        
        return dataSourceBuilder.build();
    }
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManager(EntityManagerFactoryBuilder entityManagerFactoryBuilder, DataSource dataSource) {
		
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(this.dataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.example.activemq.service");
        
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        
        return localContainerEntityManagerFactoryBean;
	}
	
	@Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
	/*@Container
    private static final GenericContainer<?> oracleContainer = new GenericContainer<>("gvenzl/oracle-free:slim-faststart")
            .withEnv("ORACLE_DATABASE", "EMPLOYE_DB")
            .withEnv("ORACLE_USER", "EMPLOYEE_USER")
            .withEnv("ORACLE_PASSWORD", "EMPLOYEE_PASSWORD")
            .withExposedPorts(1521)
            //.waitingFor(Wait.forLogMessage(".*mysqld: ready for connections.*", 2))
            //.withCopyFileToContainer(MountableFile.forClasspathResource("init.sql"), "/docker-entrypoint-initdb.d/schema.sql")
            ;
	
	@DynamicPropertySource
    private static void setupProperties(DynamicPropertyRegistry registry) {
    	//jdbc:tc:oracle:thin:localhost:1521/testcontainer
    	String url = "jdbc:tc:" + oracleContainer.getDockerImageName() + "://EMPLOYE_DB";
    	log.info("URL: [{}]", url);
    	registry.add("spring.datasource.url", () -> url);
        registry.add("spring.datasource.username", () -> "EMPLOYEE_USER");
        registry.add("spring.datasource.password", () -> "EMPLOYEE_PASSWORD");
    }
	
	
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
                .withEnv("ORACLE_PASSWORD", ORACLE_PASSWORD)
        		;

        log.info("STARTING ORACLE CONTAINER");
        CONTAINER.start();
        CONTAINER.setWaitStrategy(Wait.defaultWaitStrategy()
                .withStartupTimeout(Duration.ofSeconds(300)));
        log.info("WAITING ORACLE CONTAINER TO START");
        CONTAINER.waitingFor(
        	Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(300L))
        );
        
        String regex = ".*DATABASE IS READY TO USE\n$)";
        CONTAINER.setWaitStrategy((new LogMessageWaitStrategy())
            .withRegEx(regex)
            .withStartupTimeout(Duration.ofMinutes(5)));
        
        log.info("ORACLE CONTAINER STARTED");
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

}
