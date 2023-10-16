package com.example.school_management_system;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Bean
    public TestRestTemplate restTemplate() {
        return new TestRestTemplate();
    }
}
