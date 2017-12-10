package com.gederin.controller;

import com.gederin.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {

    private final ProductService productService;

    @RequestMapping("/")
    public String index(Model model) throws InterruptedException {
        model.addAttribute("products", productService.getProductsWithPrice());

        return "index";
    }
}