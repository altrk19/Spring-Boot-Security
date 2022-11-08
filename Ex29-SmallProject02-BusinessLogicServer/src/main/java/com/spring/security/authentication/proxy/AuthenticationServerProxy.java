package com.spring.security.authentication.proxy;

import com.spring.security.authentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationServerProxy {

    @Autowired
    private RestTemplate rest;

    @Value("${auth.server.base.url}")
    private String baseUrl;

    public boolean sendAuth(String username, String password) {
        String url = baseUrl + "/user/auth";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        HttpEntity<User> request = new HttpEntity<>(user);

        ResponseEntity<?> response = rest.postForEntity(url, request, ResponseEntity.class);
        return response.getStatusCode().equals(HttpStatus.OK);
    }

    public boolean sendOTP(String username, String code) {
        String url = baseUrl + "/otp/check";

        User user = new User();
        user.setUsername(username);
        user.setCode(code);

        HttpEntity<User> request = new HttpEntity<>(user);

        ResponseEntity<?> response = rest.postForEntity(url, request, Void.class);

        return response.getStatusCode().equals(HttpStatus.OK);
    }
}
