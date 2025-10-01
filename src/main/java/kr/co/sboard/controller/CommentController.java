package kr.co.sboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.sboard.dto.CommentDTO;
import kr.co.sboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class CommentController {


    private final CommentService commentService;



    @GetMapping("/comments/{ano}")
    public ResponseEntity<List<CommentDTO>> list(@PathVariable("ano") int ano){
        List<CommentDTO> dtoList = commentService.findAllComments(ano);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/comment/{cno}")
    public ResponseEntity<CommentDTO> comment(@PathVariable("cno") int cno){
        CommentDTO dto = commentService.findCommentById(cno);
        return ResponseEntity.ok(dto);
    }


    @PostMapping("/comment")
    public ResponseEntity<String> register(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        log.info("commentDTO={}", commentDTO);

        String regip = request.getRemoteAddr();
        commentDTO.setReg_ip(regip);

        commentService.save(commentDTO);
        return ResponseEntity.ok("success");
    }

    @PutMapping("/comment")
    public void modify(){}

    @DeleteMapping("/comment")
    public void delete(){}

}
