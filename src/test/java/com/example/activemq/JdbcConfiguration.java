package com.example.activemq;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


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
	
	@Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
    	
    	LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(this.dataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.example.activemq.entity");
        
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        
        return localContainerEntityManagerFactoryBean;
    }
	
	@Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
