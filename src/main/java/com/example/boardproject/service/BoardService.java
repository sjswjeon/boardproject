package com.example.boardproject.service;

import com.example.boardproject.model.Board;
import com.example.boardproject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Board save(Board board) {
        String newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        board.setDate(newDate);
        return boardRepository.save(board);
    }
}
