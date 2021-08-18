package com.example.boardproject.service;

import com.example.boardproject.model.Board;
import com.example.boardproject.model.User;
import com.example.boardproject.repository.BoardRepository;
import com.example.boardproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(Board board, String username) {
        String newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        User user = userRepository.findByUsername(username);
        board.setDate(newDate);
        board.setUser(user);
        return boardRepository.save(board);
    }

    public List<Board> dropUserfromBoard(Long id) {
        User user = userRepository.findById(id).orElse(null);
        List<Board> boards = boardRepository.findByUser(user);

        for (Board board : boards) {
            board.setUser(null);
        }

        return boards;
    }
}
