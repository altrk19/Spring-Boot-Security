package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoderSha512 passwordEncoderSha512;

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        UserDetails user = User.withUsername("ali")
                .password(passwordEncoderSha512.encode("1234"))
                .authorities("read")
                .build();

        inMemoryUserDetailsManager.createUser(user);

        return inMemoryUserDetailsManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
    }
}
