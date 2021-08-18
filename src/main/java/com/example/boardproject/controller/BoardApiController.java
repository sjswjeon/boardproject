package com.example.boardproject.controller;

import com.example.boardproject.model.Board;
import com.example.boardproject.repository.BoardRepository;
import com.example.boardproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
class BoardApiController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/boards")
    List<Board> all() {
        return boardRepository.findAll();
    }

    @PostMapping("/boards")
    Board newBoard(@RequestBody Board newBoard) {
        return boardRepository.save(newBoard);
    }

    @GetMapping("/boards/{id}")
    Board one(@PathVariable Long id) {

        return boardRepository.findById(id)
                .orElse(null);
    }

//    @PutMapping("/boards/{id}")
//    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(Board -> {
//                    Board.setName(newBoard.getName());
//                    Board.setRole(newBoard.getRole());
//                    return repository.save(Board);
//                })
//                .orElseGet(() -> {
//                    newBoard.setId(id);
//                    return repository.save(newBoard);
//                });
//    }

    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
    }
}
