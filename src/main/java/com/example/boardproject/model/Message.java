package com.example.boardproject.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderid;
    private Long receiverid;
    private String content;

    @ManyToOne
    @JoinColumn(name = "senderid", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "receiverid", insertable = false, updatable = false)
    private User receivedUser;
}
