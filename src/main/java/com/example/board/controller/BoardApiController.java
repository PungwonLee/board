package com.example.board.controller;


import com.example.board.dto.BoardDto;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * REST API Controller
 */
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    /* CREATE */
    @PostMapping("/board")
    public ResponseEntity save(@RequestBody BoardDto.Request dto, Principal principal) {
        return ResponseEntity.ok(boardService.save(dto, "string"));
    }

    /* READ */
    @GetMapping("/board/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.findById(id));
    }

    /* UPDATE */
    @PutMapping("/board/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody BoardDto.Request dto) {
        boardService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    /* DELETE */
    @DeleteMapping("/board/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.ok(id);
    }
}