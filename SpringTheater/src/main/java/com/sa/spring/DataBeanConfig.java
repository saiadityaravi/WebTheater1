package com.sa.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@Scope("singleton")
public class DataBeanConfig {

	@Bean
	public  DataSource getDataSource() {
		return new DataSource();
	}
}
