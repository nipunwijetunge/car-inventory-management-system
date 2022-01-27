package lk.informatics.HypeCars.HRPartnerDB;

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
		entityManagerFactoryRef = "hrpartnerEntityManagerFactory",
		transactionManagerRef = "hrpartnerTransactionManager",
		basePackages = {"lk.informatics.HypeCars.HRPartnerDB.Repo"})
public class HRPartnerDataSourceConfiguration {
	
	@Bean(name = "hrpartnerDataSourceProperties")
	@ConfigurationProperties("spring.datasource2")
	public DataSourceProperties hrpartnerDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean(name = "hrpartnerDataSource")
	@ConfigurationProperties("spring.datasource2.configuration")
	public DataSource hrpartnerDataSource(@Qualifier("hrpartnerDataSourceProperties") DataSourceProperties hrpartnerDataSourceProperties) {
		return hrpartnerDataSourceProperties().initializeDataSourceBuilder().
				type(HikariDataSource.class).build();
	}
	
	@Bean(name = "hrpartnerEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean hrpartnerEntityManagerFactory(
			EntityManagerFactoryBuilder hrpartnerEntityManagerFactoryBuilder, @Qualifier("hrpartnerDataSource") DataSource hrpartnerDataSource) {
		
		Map<String, String> hrPartnerJpaProperties = new HashMap<>();
        hrPartnerJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hrPartnerJpaProperties.put("hibernate.hbm2ddl.auto", "none");
        
        return hrpartnerEntityManagerFactoryBuilder
        		.dataSource(hrpartnerDataSource)
        		.packages("lk.informatics.HypeCars.HRPartnerDB.Entities")
        		.persistenceUnit("hrpartnerDataSource")
        		.properties(hrPartnerJpaProperties)
        		.build();
	}
	
	@Bean(name = "hrpartnerTransactionManager")
	public PlatformTransactionManager hrpartnerTransactionManager(
			@Qualifier("hrpartnerEntityManagerFactory") EntityManagerFactory hrpartnerEntityManagerFactory) {
		
		return new JpaTransactionManager(hrpartnerEntityManagerFactory);
	}
}
