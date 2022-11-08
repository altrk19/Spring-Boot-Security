package com.spring.security.controllers;

import com.spring.security.model.Product;
import com.spring.security.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/find")
    public List<Product> findProducts() {
        return productService.findProducts();
    }
}
