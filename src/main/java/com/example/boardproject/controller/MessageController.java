package com.example.boardproject.controller;

import com.example.boardproject.model.Message;
import com.example.boardproject.model.User;
import com.example.boardproject.repository.MessageRepository;
import com.example.boardproject.repository.UserRepository;
import com.example.boardproject.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageService messageService;

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {

        if (id == null) {
            model.addAttribute("message", new Message());
        } else {
            Message receivedMessage = messageRepository.findById(id).orElse(null);
            Long senderid = receivedMessage.getSenderid();
            Message message = new Message();
            message.setReceiverid(senderid);
            model.addAttribute("message", message);
        }
        return "message/form";
    }

    @PostMapping("/form")
    public String send(@ModelAttribute Message message, Authentication authentication) {
        String senderUserName = authentication.getName();
        messageService.send(message, senderUserName);
        return "redirect:/message/list";
    }

    @GetMapping("/list")
    public String list(Model model, Authentication authentication) {
        String username = authentication.getName();
        List<Message> messages = messageService.sentList(username);
        List<Message> receivedMessage = messageService.receivedList(username);
        model.addAttribute("messages", messages);
        model.addAttribute("receivedmessages", receivedMessage);
        return "message/list";
    }
}
