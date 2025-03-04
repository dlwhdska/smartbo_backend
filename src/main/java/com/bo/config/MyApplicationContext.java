package com.bo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableTransactionManagement
@Slf4j
@PropertySource("classpath:/db.properties")
public class MyApplicationContext {
	@Value("${spring.datasource.hikari.driver-class-name}")
	private String hikariDriverClassName;
	
	@Autowired
	Environment env;

	@Bean
	@ConfigurationProperties("spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		log.info("IN hikariConfig()-1");
		HikariConfig config = new HikariConfig();
		
		return config;
	}
	
	@Bean
	public DataSource dataSourceHikari() {
		return new HikariDataSource(hikariConfig());
	}
	
}
