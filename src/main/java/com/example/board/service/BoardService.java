package com.example.board.service;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /* CREATE */
    @Transactional
    public Long save(BoardDto.Request dto, String name) {
        /* User 정보를 가져와 dto에 담아준다. */
        Member member = memberRepository.findByName(name);
        dto.setMember(member);
        log.info("PostsService save() 실행");
        Board board = dto.toEntity();
        boardRepository.save(board);

        return board.getId();
    }

    /* READ 게시글 리스트 조회 readOnly 속성으로 조회속도 개선 */
    @Transactional(readOnly = true)
    public BoardDto.Response findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        return new BoardDto.Response(board);
    }

    /* UPDATE (dirty checking 영속성 컨텍스트)
     *  User 객체를 영속화시키고, 영속화된 User 객체를 가져와 데이터를 변경하면
     * 트랜잭션이 끝날 때 자동으로 DB에 저장해준다. */
    @Transactional
    public void update(Long id, BoardDto.Request dto) {
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        board.update(dto.getTitle(), dto.getContent());
    }

    /* DELETE */
    @Transactional
    public void delete(Long id) {
        Board posts = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));

        boardRepository.delete(posts);
    }

    /* Views Counting */
    @Transactional
    public int updateView(Long id) {
        return boardRepository.updateView(id);
    }


    /* Paging and Sort */
    @Transactional(readOnly = true)
    public Page<Board> pageList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }


}