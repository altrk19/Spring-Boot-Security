package com.spring.security.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Async
    public String getName() {
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println("test2");
        return context.getAuthentication().getName();
    }
}
