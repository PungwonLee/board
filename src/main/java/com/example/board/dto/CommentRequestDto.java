package com.example.board.dto;

import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {
    private Long id;
    private String comment;
    private Member member;
    private Board board;

    /* Dto -> Entity */
    public Comment toEntity() {
        Comment comments = Comment.builder()
                .id(id)
                .comment(comment)
                .member(member)
                .board(board)
                .build();

        return comments;
    }
}