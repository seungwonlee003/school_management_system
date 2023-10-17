package com.example.school_management_system.configuration;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public TestRestTemplate restTemplate() {
        return new TestRestTemplate();
    }
}
