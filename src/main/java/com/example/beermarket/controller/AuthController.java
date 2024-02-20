package com.example.beermarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Ошибка аутентификации");
        }
        return "login";
    }

    @GetMapping("/unauthorized")
    public String unauthorizedPage() {
        return "unauthorized";
    }
}
