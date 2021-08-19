package com.example.boardproject.controller;

import com.example.boardproject.model.User;
import com.example.boardproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<User> members = userRepository.findAll();
        model.addAttribute("members", members);
        return "member/list";
    }
}
