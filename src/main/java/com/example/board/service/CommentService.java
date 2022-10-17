package com.example.board.service;

import com.example.board.dto.CommentRequestDto;
import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    /* CREATE */
    @Transactional
    public Long commentSave(String name, Long id, CommentRequestDto dto) {
        Member member = memberRepository.findByName(name);
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));

        dto.setMember(member);
        dto.setBoard(board);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();
    }
}