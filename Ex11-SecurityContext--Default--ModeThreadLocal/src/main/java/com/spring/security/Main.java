package com.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}

// username: user
// password: spring security generates passw