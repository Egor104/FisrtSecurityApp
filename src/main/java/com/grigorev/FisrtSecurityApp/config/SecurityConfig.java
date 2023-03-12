package com.grigorev.FisrtSecurityApp.config;

import com.grigorev.FisrtSecurityApp.security.AuthProviderImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Внедряем AuthProvider
    private final AuthProviderImpl authProvider;

    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }


    // Настраивает аутентификацию, передаём authProvider, давая понять SpringSecurity,
    // что мы используем именно его для аутентификации пользователей
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider); //TODO
    }
}
