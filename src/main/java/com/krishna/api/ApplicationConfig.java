/*******************************************************************************
 * Copyright 2018 Jaguar Land Rover Ltd. all rights reserved.
 ******************************************************************************/
package com.krishna.api;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.krishna.api")
public class ApplicationConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

	@Value("${krishna.datasource.url}")
	private String dbUrl;

	@Value("${krishna.datasource.username}")
	private String dbUserName;

	@Value("${krishna.datasource.password}")
	private String dbPassword;

	@Bean(name = "krishnaDataSource")
	public DataSource dataSource() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("PROPERTY FROM JAR SOP: {} {}", dbUserName, dbUrl);
		}
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUserName);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}

	@Bean(name = "krishnaJdbcTemplate")
	public JdbcTemplate jdbcTemplate(DataSource krishnaDataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(krishnaDataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}

	@Bean(name = "krishnaNamedParamJdbcTemplate")
	public NamedParameterJdbcTemplate krishnaNamedParameterJdbcTemplate(DataSource krishnaDataSource) {
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(krishnaDataSource);
		return jdbcTemplate;
	}

	@Bean
	public SimpleJdbcInsert simpleJdbcInsert(DataSource krishnaDataSource) {
		return new SimpleJdbcInsert(krishnaDataSource);
	}

}
