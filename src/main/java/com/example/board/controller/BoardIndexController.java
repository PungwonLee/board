package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.dto.CommentDto;
import com.example.board.dto.CommentResponseDto;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardIndexController {

    private final BoardService boardService;
    

    /* 글 상세보기 */
    @GetMapping("/board/read/{id}")
    public BoardDto.Response read(@PathVariable Long id, Principal principal, Model model) {
        BoardDto.Response dto = boardService.findById(id);
        List<CommentDto.Response> comments = dto.getComments();

        /* 댓글 관련 */
        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }

        /* 사용자 관련 */
        if (principal != null) {
            model.addAttribute("user", principal.getName());
//
//            /*게시글 작성자 본인인지 확인*/
//            if (dto.getMemberId().equals(principal.getId())) {
//                model.addAttribute("writer", true);
//            }
        }
        boardService.updateView(id); // views ++
        model.addAttribute("posts", dto);
        return dto;
    }

    @GetMapping("/board/update/{id}")
    public BoardDto.Response  read(@PathVariable Long id, Model model) {
        BoardDto.Response dto = boardService.findById(id);
        boardService.updateView(id); // views ++
        model.addAttribute("posts", dto);

        return dto;
    }
}