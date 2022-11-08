package com.spring.security.config;

import com.spring.security.filters.CsrfTokenLogger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
                .authorizeRequests().anyRequest().permitAll();
    }
}

//Note: Need to add X-CSRF-TOKEN:<token> header into reqeust

//Note2: Csrf token is provided on server log for this sample. It should be in the http response. Check next sample

//if you provide the correct value for the CSRF token, the call is successful. You also
//need to specify the session ID (JSESSIONID) because the default implementation of
//CsrfTokenRepository stores the value of the CSRF token on the session
// (no need to add JSESSIONID for postman)