package lk.informatics.HypeCars.CustoDB;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "customerEntityManagerFactory",
		transactionManagerRef = "customerTransactionManager",
		basePackages = {"lk.informatics.HypeCars.CustoDB.Repo"})

public class CustomerDataSourceConfiguration {
	
	// Primary data source
	@Primary
	@Bean(name = "customerDataSourceProperties")
	@ConfigurationProperties("spring.datasource1")
	public DataSourceProperties customerDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Primary
	@Bean(name = "customerDataSource")
	@ConfigurationProperties("spring.datasource1.configuration")
	public DataSource customerDataSource(@Qualifier("customerDataSourceProperties") DataSourceProperties customerDataSourceProperties) {
		return customerDataSourceProperties().initializeDataSourceBuilder().
				type(HikariDataSource.class).build();
	}
	
	@Primary
	@Bean(name = "customerEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("customerDataSource") DataSource primaryDataSource) {
		
		Map<String, String> customerJpaProperties = new HashMap<>();
        customerJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        customerJpaProperties.put("hibernate.hbm2ddl.auto", "none");
		
		return builder
				.dataSource(primaryDataSource)
				.packages("lk.informatics.HypeCars.CustoDB.Entities")
				.persistenceUnit("customerDataSource")
				.properties(customerJpaProperties)
				.build();
	}
	
	@Primary
	@Bean(name = "customerTransactionManager")
	public PlatformTransactionManager customerTransactionManager(
			@Qualifier("customerEntityManagerFactory") EntityManagerFactory customerEntityManagerFactory) {
		
		return new JpaTransactionManager(customerEntityManagerFactory);
	}
	
}
