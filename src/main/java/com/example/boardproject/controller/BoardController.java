package com.example.boardproject.controller;

import com.example.boardproject.model.Board;
import com.example.boardproject.repository.BoardRepository;
import com.example.boardproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    //    전체 게시물 목록
    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    //    게시물 작성 폼 페이지
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("board", new Board());
        return "board/form";
    }

    //    게시물 업로드
    @PostMapping("/form")
    public String postForm(@ModelAttribute Board board) {
        boardService.save(board);
        return "redirect:/board/list";
    }
}
