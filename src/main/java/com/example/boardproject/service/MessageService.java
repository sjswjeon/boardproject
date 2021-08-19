package com.example.boardproject.service;

import com.example.boardproject.model.Message;
import com.example.boardproject.model.User;
import com.example.boardproject.repository.MessageRepository;
import com.example.boardproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public Message send(Message message, String senderUserName) {
        User user = userRepository.findByUsername(senderUserName);
        message.setSenderid(user.getId());
        return messageRepository.save(message);
    }
}
