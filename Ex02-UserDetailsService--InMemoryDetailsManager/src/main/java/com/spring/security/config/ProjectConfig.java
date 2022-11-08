package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        ////---------AUTHENTICATION---------////
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        UserDetails user = User.withUsername("ali")
                .password("1234")
                .authorities("read")
                .build();

        inMemoryUserDetailsManager.createUser(user);

        return inMemoryUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();    // NoOpPasswordEncoder instance treats passwords as plain text
                                                     // It doesn’t encrypt or hash them. For matching, NoOpPasswordEncoder only compares the strings using the underlying equals(Object o) method of the String class
                                                    // You shouldn’t use this type of PasswordEncoder in a production-ready app
    }

    ////---------AUTHENTICATION---------////


    ////---------AUTHORIZATION---------////
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
//        http.authorizeRequests().anyRequest().authenticated();            // it is the same behavior as default
        http.authorizeRequests().anyRequest().permitAll();              // all endpoints are accessible
    }
}
