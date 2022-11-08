package com.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    ////---------AUTHENTICATION---------////
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("ali").password("1234").authorities("read").build();
        inMemoryUserDetailsManager.createUser(user);
        auth.userDetailsService(inMemoryUserDetailsManager).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    ////---------AUTHENTICATION---------////


    ////---------AUTHORIZATION---------////
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();            // it is the same behavior as default
//        http.authorizeRequests().anyRequest().permitAll();              // all endpoints are accessible
    ////---------AUTHORIZATION---------////
    }
}
