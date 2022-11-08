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
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        UserDetails john = User.withUsername("john").password("12345").authorities("READ").build();
        UserDetails jane = User.withUsername("jane").password("12345").authorities("WRITE").build();
        UserDetails jack = User.withUsername("jack").password("12345").authorities("READ","WRITE","DELETE").build();

        userDetailsService.createUser(john);
        userDetailsService.createUser(jane);
        userDetailsService.createUser(jack);
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().hasAuthority("WRITE");
//        http.authorizeRequests().anyRequest().access("hasAuthority('WRITE')");
//        http.authorizeRequests().anyRequest().hasAnyAuthority("WRITE", "READ");
//        http.authorizeRequests().anyRequest().access("hasAuthority('READ') and !hasAuthority('DELETE')");
//        http.authorizeRequests().anyRequest().access("T(java.time.LocalTime).now().isAfter(T(java.time.LocalTime).of(12, 0))");      //A straightforward example would be to configure access to the endpoint to be allowed only after 12:00 pm
//        http.authorizeRequests().anyRequest().denyAll();   // restrict access for everyone
    }
}
