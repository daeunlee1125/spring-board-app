package kr.co.sboard.service;


import kr.co.sboard.dto.CommentDTO;
import kr.co.sboard.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    public List<CommentDTO> findAllComments(int ano){
        List<CommentDTO> dtoList = commentMapper.selectAll(ano);
        return dtoList;
    }

    public CommentDTO findCommentById(int cno){
        CommentDTO dto = commentMapper.select(cno);
        return dto;
    }

    public CommentDTO save(CommentDTO commentDTO){
        commentMapper.insert(commentDTO);
        return findCommentById(commentDTO.getCno());
    }

    public void update(CommentDTO commentDTO){
        commentMapper.update(commentDTO);
    }

    public void delete(int cno){
        commentMapper.delete(cno);
    }
}
