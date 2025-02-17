package com.andersonjunior.calltick.config;

import java.text.ParseException;

import com.andersonjunior.calltick.services.ProdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Autowired
	private ProdService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
    
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateProdDatabase();
		return true;
	}

}
