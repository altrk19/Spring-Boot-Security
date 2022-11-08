package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        String usersByUsernameQuery = "select username, password, enabled from spring.users where username = ?";
        String authsByUserQuery = "select username, authority from spring.authorities where username = ?";
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
    }
}
