package com.example.board.dto;

import com.example.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createdDate, modifiedDate;
    private int view;
    private Long memberId;
    private List<CommentResponseDto> comments;

    /* Entity -> Dto*/
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.writer = board.getWriter();
        this.content = board.getContent();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
        this.view = board.getView();
        this.memberId = board.getMember().getId();
        this.comments = board.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}