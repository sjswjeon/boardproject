package com.example.boardproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/signin")
    public String signIn() {
        return "account/signin";
    }

    @GetMapping("/register")
    public String register() {
        return "account/register";
    }
}
