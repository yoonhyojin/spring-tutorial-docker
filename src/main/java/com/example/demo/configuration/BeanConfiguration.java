package com.example.demo.configuration;

import com.example.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserService userService() {
        return new UserService();
    }
}
