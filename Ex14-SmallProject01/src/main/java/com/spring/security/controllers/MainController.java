package com.spring.security.controllers;

import com.spring.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @GetMapping("/main")
    public String main(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("products", productService.findAll());
        return "main.html";
    }
}
