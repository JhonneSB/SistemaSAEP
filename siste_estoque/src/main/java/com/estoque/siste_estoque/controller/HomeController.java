package com.estoque.siste_estoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/principal")
    public String principal(Model model) {
        return "principal";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}

