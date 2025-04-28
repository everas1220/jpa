package com.example.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.jpa.entity.Board;
import com.example.jpa.entity.QBoard;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void queryDslTest() {

        QBoard board = QBoard.board;
        // where b.title = 'title1'
        // System.out.println(boardRepository.findAll(board.title.eq("board Title1")));

        // where b.title like = 'title1%'
        // System.out.println(boardRepository.findAll(board.title.startsWith("board")));

        // where b.title like = '%title1%'
        // System.out.println(boardRepository.findAll(board.title.contains("board")));

        // where b.title like = '%title1%' and b.bno > 0 order by bno desc
        Iterable<Board> boards = boardRepository.findAll(board.title.contains("Title1")
                .and(board.bno.gt(0L)), Sort.by("bno").descending());
        System.out.println(boards);

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(board.bno.gt(0L), pageable);

        System.out.println("page size" + result.getSize());
        System.out.println("page TotalPage" + result.getTotalPages());
        System.out.println("page TotalElements" + result.getTotalElements());
        System.out.println("page content" + result.getContent());
    }
}
