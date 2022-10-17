package com.example.board.dto;


import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
public class CommentDto {

    /** 댓글 Service 요청을 위한 DTO 클래스 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
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

    /**
     * 댓글 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */
    @RequiredArgsConstructor
    @Getter
    public static class Response {
        private Long id;
        private String comment;
        private String name;
        private Long boardId;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public Response(Comment comment) {
            this.id = comment.getId();
            this.comment = comment.getComment();
            this.name = comment.getMember().getName();
            this.boardId = comment.getBoard().getId();
            this.createdDate=comment.getCreatedDate();
            this.modifiedDate=comment.getModifiedDate();
        }
    }
}