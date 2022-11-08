package com.spring.security.controllers;

import com.spring.security.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() throws ExecutionException, InterruptedException {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            return "Hello, " + executorService.submit(new DelegatingSecurityContextCallable<>(task)).get() + "!";
        } finally {
            executorService.shutdown();
        }

    }

    @GetMapping("/hello2")
    public String hello2() throws ExecutionException, InterruptedException {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        ExecutorService executorService = new DelegatingSecurityContextExecutorService(Executors.newCachedThreadPool());
        try {
            return "Hello, " + executorService.submit(task).get() + "!";
        } finally {
            executorService.shutdown();
        }

    }

}
