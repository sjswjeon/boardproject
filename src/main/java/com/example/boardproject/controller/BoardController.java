package com.example.boardproject.controller;

import com.example.boardproject.model.Board;
import com.example.boardproject.repository.BoardRepository;
import com.example.boardproject.service.BoardService;
import com.example.boardproject.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardValidator boardValidator;

    //    전체 게시물 목록
    @GetMapping("/list")
    public String list(Model model,@PageableDefault(size = 4) Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);

        model.addAttribute("boards", boards);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/list";
    }

    //    게시물 작성 폼 페이지
    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    //    게시물 업로드
    @PostMapping("/form")
    public String postForm(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        boardService.save(board);
        return "redirect:/board/list";
    }

    @GetMapping("/readpost")
    public String readPost(Model model, @RequestParam Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        model.addAttribute("board", board);
        return "board/readpost";
    }
}
