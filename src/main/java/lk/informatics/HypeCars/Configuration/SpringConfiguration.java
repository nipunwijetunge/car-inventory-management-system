//package lk.informatics.HypeCars.Configuration;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//@Configuration
//public class SpringConfiguration {
//	
//	@Autowired
//	private Environment env;
//	
//	@Bean
//	@Primary
//	public DataSource customerDataSource() {
//		DriverManagerDataSource datasource = new DriverManagerDataSource();
//		
//		datasource.setDriverClassName(env.getProperty("spring.datasource1.driver-class-name"));
//		datasource.setUrl(env.getProperty("spring.datasource1.url"));
//		datasource.setUsername(env.getProperty("spring.datasource1.username"));
//		datasource.setPassword(env.getProperty("spring.datasource1.password"));
//		
//		return datasource;
//	}
//	
//	@Bean
//	public DataSource hrpartnerDataSource() {
//		DriverManagerDataSource datasource = new DriverManagerDataSource();
//		
//		datasource.setDriverClassName(env.getProperty("spring.datasource2.driver-class-name"));
//		datasource.setUrl(env.getProperty("spring.datasource2.url"));
//		datasource.setUsername(env.getProperty("spring.datasource2.username"));
//		datasource.setPassword(env.getProperty("spring.datasource2.password"));
//		
//		return datasource;
//	}
//	
//	@Bean
//	public DataSource hypeinventoryDataSource() {
//		DriverManagerDataSource datasource = new DriverManagerDataSource();
//		
//		datasource.setDriverClassName(env.getProperty("spring.datasource3.driver-class-name"));
//		datasource.setUrl(env.getProperty("spring.datasource3.url"));
//		datasource.setUsername(env.getProperty("spring.datasource3.username"));
//		datasource.setPassword(env.getProperty("spring.datasource3.password"));
//		
//		return datasource;
//	}
//	
//	@Bean
//	public JdbcTemplate customerJdbcTemplate(@Qualifier("customerDataSource") DataSource ds) {
//		return new JdbcTemplate(ds);
//	}
//	
//	@Bean
//	public JdbcTemplate hrpartnerJdbcTemplate(@Qualifier("hrpartnerDataSource") DataSource ds) {
//		return new JdbcTemplate(ds);
//	}
//	
//	@Bean
//	public JdbcTemplate hypeinventoryJdbcTemplate(@Qualifier("hypeinventoryDataSource") DataSource ds) {
//		return new JdbcTemplate(ds);
//	}
//}
