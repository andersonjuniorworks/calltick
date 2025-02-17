package com.andersonjunior.calltick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@EntityScan(basePackages = "com.andersonjunior.calltick.models")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CalltickApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalltickApplication.class, args);
	}
	
}
