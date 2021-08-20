package com.example.boardproject.controller;

import com.example.boardproject.model.User;
import com.example.boardproject.repository.UserRepository;
import com.example.boardproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/signin")
    public String signIn() {
        return "account/signin";
    }

    @GetMapping("/register")
    public String register() {
        return "account/register";
    }

    @PostMapping("/register")
    public String saveUser(User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/personalinfo")
    public String personalInfo(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "account/personalinfo";
    }

    @GetMapping("/updateinfo")
    public String updateInfo() {
        return "account/updateinfo";
    }
}
