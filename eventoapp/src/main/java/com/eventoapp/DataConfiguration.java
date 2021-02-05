package com.eventoapp;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataConfiguration {
	
//	@Bean
//	public DataSource dataSource() {
//		//DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		//dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		//dataSource.setUrl("jdbc:mysql://localhost:3306/eventoapp");
//		//dataSource.setUrl("jdbc:mysql://localhost:3306/e?useTimezone=true&amp&serverTimezone=UTC");
//		//dataSource.setUsername("root");
//		//dataSource.setPassword("kiko");
//		//return dataSource();
//		
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		
//		dataSource.setUsername("root");
//		dataSource.setPassword("kiko");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/eventoapp");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/eventoapp?useTimezone=true&amp&serverTimezone=UTC");
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		return dataSource;
//	}
//	
//	@Bean
//	public JpaVendorAdapter jpaVendorAdapter() {
//		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//		adapter.setDatabase(Database.MYSQL);
//		adapter.setShowSql(true);
//		adapter.setGenerateDdl(true);
//		adapter.setDatabasePlatform("org.hibernate.dialect.MariaDBDialect");
//		adapter.setPrepareConnection(true);
//		return adapter;
//	}
	
	 @Bean
	    public BasicDataSource dataSource() throws URISyntaxException {
	        URI dbUri = new URI(System.getenv("DATABASE_URL"));

	        String username = dbUri.getUserInfo().split(":")[0];
	        String password = dbUri.getUserInfo().split(":")[1];
	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

	        BasicDataSource basicDataSource = new BasicDataSource();
	        basicDataSource.setUrl(dbUrl);
	        basicDataSource.setUsername(username);
	        basicDataSource.setPassword(password);

	        return basicDataSource;
	    }

}
