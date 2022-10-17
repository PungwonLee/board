package com.example.board.dto;

import com.example.board.entity.Comment;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long id;
    private String comment;
    private String name;
    private Long boardId;
    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.name = comment.getMember().getName();
        this.boardId = comment.getBoard().getId();
        this.createdDate=comment.getCreatedDate();
        this.modifiedDate=comment.getModifiedDate();
    }
}