package com.andersonjunior.calltick.config;

import java.text.ParseException;

import com.andersonjunior.calltick.services.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
	private TestService testService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		testService.instantiateTestDatabase();
		return true;
	}
    
}