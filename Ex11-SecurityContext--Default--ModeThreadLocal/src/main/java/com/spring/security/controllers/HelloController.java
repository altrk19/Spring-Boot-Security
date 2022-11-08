package com.spring.security.controllers;

import com.spring.security.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(Authentication a) {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication a = context.getAuthentication();
        return "Hello, " + a.getName() + "!";
        //Output:Hello, user!

    }

    @GetMapping("/helloAsync")
    public String helloAsync() {
        System.out.println("test1");
        String username = helloService.getName();
        return "Async Hello, " + username + "!";
        //Output:Async Hello, null!
    }
}
