package com.HAH.jdbc.model.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan(basePackages = "com.HAH.jdbc.model.dao")
@PropertySource("/sql.properties")
public class AppConfig {

	@Bean
	public DataSource getHsqlDataSource() {
		var builder = new EmbeddedDatabaseBuilder();
		builder.setType(EmbeddedDatabaseType.HSQL);
		builder.setName("hsqlDatasource");
		builder.addScript("classpath:/database.sql");
		return builder.build();
	}

	@Bean
	public JdbcTemplate getTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public SimpleJdbcInsert categoryInsert(DataSource dataSource) {
		var insert = new SimpleJdbcInsert(dataSource);
		insert.setGeneratedKeyName("id");
		insert.setTableName("category");
		return insert;
	}
}
