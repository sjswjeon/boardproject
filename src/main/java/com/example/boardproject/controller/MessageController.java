package com.example.boardproject.controller;

import com.example.boardproject.model.Message;
import com.example.boardproject.repository.MessageRepository;
import com.example.boardproject.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("message", new Message());
        return "message/form";
    }

    @PostMapping("/form")
    public String send(@ModelAttribute Message message, Authentication authentication) {
        String senderUserName = authentication.getName();
        messageService.send(message, senderUserName);
        return "redirect:/message/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "message/list";
    }
}
