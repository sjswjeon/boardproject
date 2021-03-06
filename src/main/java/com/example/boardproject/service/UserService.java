package com.example.boardproject.service;

import com.example.boardproject.model.Role;
import com.example.boardproject.model.User;
import com.example.boardproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        user.setDate(newDate);
        user.setLevel("Level1");
        user.setPoint(0L);
        Role role = new Role();
        role.setId(1L);
        user.getRoles().add(role);

        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUser(Long id) {
        boardService.dropUserfromBoard(id);
        User user = userRepository.findById(id).orElse(null);
        user.getRoles().clear();

        userRepository.deleteById(id);
    }
}
