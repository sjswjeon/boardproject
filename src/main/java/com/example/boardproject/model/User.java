package com.example.boardproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String name;
    private String email;
    private String phonenumber;
    private boolean enabled;
    private String date;
    private String gender;
    private String level;
    private Long point;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "receivedUser")
    private List<Message> receivedMessages = new ArrayList<>();
}
