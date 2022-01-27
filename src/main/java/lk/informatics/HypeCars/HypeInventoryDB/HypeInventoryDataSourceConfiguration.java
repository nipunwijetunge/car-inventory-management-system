package lk.informatics.HypeCars.HypeInventoryDB;

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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "hypeinventoryEntityManagerFactory",
		transactionManagerRef = "hypeinventoryTransactionManager",
		basePackages = "lk.informatics.HypeCars.HypeInventoryDB.Repo")
public class HypeInventoryDataSourceConfiguration {
	
	@Bean(name = "hypeinventoryDataSourceProperties")
	@ConfigurationProperties("spring.datasource3")
	public DataSourceProperties hypeinventoryDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean(name = "hypeinventoryDataSource")
	@ConfigurationProperties("spring.datasource3.configuration")
	public DataSource hypeinventoryDataSource(@Qualifier("hypeinventoryDataSourceProperties") DataSourceProperties hypeinventoryDataSourceProperties) {
		return hypeinventoryDataSourceProperties().initializeDataSourceBuilder().
				type(HikariDataSource.class).build();
	}
	
	@Bean(name = "hypeinventoryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean hypeinventoryEntityManagerFactory(
			EntityManagerFactoryBuilder hypeinventoryEntityManagerFactoryBuilder, @Qualifier("hypeinventoryDataSource") DataSource hypeinventoryDataSource) {
		
		Map<String, String> hypeinventoryJpaProperties = new HashMap<>();
        hypeinventoryJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hypeinventoryJpaProperties.put("hibernate.hbm2ddl.auto", "none");
        
        return hypeinventoryEntityManagerFactoryBuilder
        		.dataSource(hypeinventoryDataSource)
        		.packages("lk.informatics.HypeCars.HypeInventoryDB.Entities")
        		.persistenceUnit("hypeinventoryDataSource")
        		.properties(hypeinventoryJpaProperties)
        		.build();
	}
	
	@Bean(name = "hypeinventoryTransactionManager")
	public PlatformTransactionManager hypeinventoryTransactionManager(
			@Qualifier("hypeinventoryEntityManagerFactory") EntityManagerFactory hypeinventoryEntityManagerFactory) {
		
		return new JpaTransactionManager(hypeinventoryEntityManagerFactory);
	}
}
