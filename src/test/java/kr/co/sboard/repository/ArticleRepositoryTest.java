package kr.co.sboard.repository;

import com.querydsl.core.Tuple;
import kr.co.sboard.repository.custom.ArticleRepositoryCustom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleRepositoryTest {
    @Autowired
    ArticleRepository articleRepository;




    @Test
    void test1(){
        /*
        Pageable pageable = PageRequest.of(1, 10); // of(페이지 번호, 페이지 크기)


        Page<Tuple> pageTuple = articleRepository.selectArticleAllForList(pageable);

        List<Tuple> list = pageTuple.getContent();

        System.out.println(list);
         */
    }
}