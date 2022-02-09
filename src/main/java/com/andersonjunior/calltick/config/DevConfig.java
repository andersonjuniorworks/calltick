package com.andersonjunior.calltick.config;

import java.text.ParseException;

import com.andersonjunior.calltick.services.EmailService;
import com.andersonjunior.calltick.services.SmtpEmailService;
import com.andersonjunior.calltick.services.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {
    
    @Autowired
    private TestService testService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		testService.instantiateTestDatabase();
		return true;
	}

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }

}
