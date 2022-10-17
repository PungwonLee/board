package com.example.board.dto;


import com.example.board.entity.Board;
import com.example.board.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
public class BoardDto {


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;
        private String title;
        private String writer;
        private String content;
        private LocalDateTime createdDate, modifiedDate;
        private int view;
        private Member member;

        /* Dto -> Entity */
        public Board toEntity() {
            Board board = Board.builder()
                    .id(id)
                    .title(title)
                    .writer(writer)
                    .content(content)
                    .view(0)
                    .member(member)
                    .build();

            return board;
        }
    }

    /**
     * 게시글 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */
    @Getter
    public static class Response {
        private final Long id;
        private final String title;
        private final String writer;
        private final String content;
        private final LocalDateTime createdDate, modifiedDate;
        private final int view;
        private final Long userId;
        private final List<CommentDto.Response> comments;

        /* Entity -> Dto*/
        public Response(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.writer = board.getWriter();
            this.content = board.getContent();
            this.createdDate = board.getCreatedDate();
            this.modifiedDate = board.getModifiedDate();
            this.view = board.getView();
            this.userId = board.getMember().getId();
            this.comments = board.getComments().stream().map(CommentDto.Response::new).collect(Collectors.toList());
        }
    }
}