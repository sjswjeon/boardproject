package com.example.boardproject.controller;

import com.example.boardproject.model.User;
import com.example.boardproject.repository.UserRepository;
import com.example.boardproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
class UserApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<User> all() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }


    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {

        return userRepository.findById(id)
                .orElse(null);
    }

//    @PutMapping("/users/{id}")
//    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
//
////        return userRepository.findById(id)
////                .map(User -> {
////                    User.setName(newUser.getName());
////                    User.setRole(newUser.getRole());
////                    return userRepository.save(User);
////                })
////                .orElseGet(() -> {
////                    newUser.setId(id);
////                    return userRepository.save(newUser);
////                });
//    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
