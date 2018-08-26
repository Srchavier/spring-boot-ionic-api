package com.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cursomc.service.EmailService;
import com.cursomc.service.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
