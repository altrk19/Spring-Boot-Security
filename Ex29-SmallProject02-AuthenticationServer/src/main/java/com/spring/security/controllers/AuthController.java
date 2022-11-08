package com.spring.security.controllers;

import com.spring.security.entities.Otp;
import com.spring.security.entities.User;
import com.spring.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PostMapping("/user/auth")
    public void auth(@RequestBody User user, HttpServletResponse response) {
        try {
            userService.auth(user);  // if login succeed, generates otp and saves then returns 200
        } catch (BadCredentialsException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @PostMapping("/otp/check")
    public void check(@RequestBody Otp otp, HttpServletResponse response) {
        if (userService.check(otp)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
