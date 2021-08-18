package com.example.boardproject.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
