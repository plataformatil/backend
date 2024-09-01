package com.til.users.infraestructure.config;

import com.til.users.application.services.AuthenticationService;
import com.til.users.application.services.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public AuthenticationService authentication() {
        return new AuthenticationService();
    }

    @Bean
    public LoginService login() {
        return new LoginService();
    }
}
