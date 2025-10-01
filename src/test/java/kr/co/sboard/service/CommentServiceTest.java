package kr.co.sboard.service;

import kr.co.sboard.dto.CommentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    void findAllComments() {
        List<CommentDTO> dtoList = commentService.findAllComments(6);
        System.out.println(dtoList);
    }

    @Test
    void findCommentById() {
    }

    @Test
    void save() {
        CommentDTO commentDTO = CommentDTO.builder()
                .ano(1050)
                .content("1050번 댓글 1")
                .writer("a206")
                .reg_ip("127.0.0.1")
                .build();
        CommentDTO saved = commentService.save(commentDTO);
        System.out.println(saved);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}