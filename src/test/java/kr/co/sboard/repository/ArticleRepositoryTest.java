package kr.co.sboard.repository;

import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.repository.custom.ArticleRepositoryCustom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleRepositoryTest {
    @Autowired
    ArticleRepository articleRepository;




    @Test
    void test1(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        Pageable pageable = pageRequestDTO.getPageable("ano"); // of(페이지 번호, 페이지 크기)


        Page<Tuple> pageTuple = articleRepository.selectArticleAllForList(pageRequestDTO, pageable);

        List<Tuple> list = pageTuple.getContent();

        System.out.println(list);

    }

    @Test
    @Transactional
    void test2(){

        Optional<Article> optArticle = articleRepository.findById(6);
        if (optArticle.isPresent()){
            Article article = optArticle.get();
            System.out.println(article);
        }

    }
}