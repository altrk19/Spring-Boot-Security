package com.spring.security.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @GetMapping("/main")
    public String main(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        return "main.html";
    }

}
