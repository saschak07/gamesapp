package com.gameapp.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Value("${security.user.name}")
    private String userName;
    @Value("${security.user.password}")
    private String password;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
            .inMemoryAuthentication().withUser(userName)
            .password(passwordEncoder().encode(password)).roles("USER");
    }


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http
            .csrf().disable().authorizeRequests().antMatchers("/*")
            .authenticated().and().httpBasic();
    }

}
