package kr.co.sboard.service;

import com.querydsl.core.Tuple;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public ArticleDTO getArticle(int ano){
        Optional<Article> optArticle = articleRepository.findById(ano);
        if(optArticle.isPresent()){
            Article article = optArticle.get();
            return modelMapper.map(article, ArticleDTO.class);

        }
        return null;
    }
    public PageResponseDTO getArticleAll(PageRequestDTO pageRequestDTO){

        // List<Article> articles = articleRepository.findAll();
        Pageable pageable = pageRequestDTO.getPageable("ano");

        Page<Tuple> pageTuple = null;

        if (pageRequestDTO.getSearchType() != null){
            // 검색 글 목록
            pageTuple = articleRepository.selectArticleAllForSearch(pageRequestDTO, pageable);
            
        } else {
            // 일반 글 목록
            pageTuple = articleRepository.selectArticleAllForList(pageRequestDTO, pageable);
        }



        List<Tuple>  tupleList = pageTuple.getContent();
        int total = (int) pageTuple.getTotalElements();

        java.util.List<ArticleDTO> dtoList = tupleList.stream()
                .map(tuple -> {
                    Article article = tuple.get(0, Article.class);
                    String nick = tuple.get(1, String.class);

                    article.setNick(nick);

                    return modelMapper.map(article, ArticleDTO.class); // 람다식에서 이케 리턴된 값이 .다음으로 넘어감

                })
                .toList();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();

    }
    public int save(ArticleDTO articleDTO){
        Article article = modelMapper.map(articleDTO, Article.class);
        Article savedArticle= articleRepository.save(article);

        return savedArticle.getAno();
    }
    public void modify(ArticleDTO articleDTO){
        if (articleRepository.existsById(articleDTO.getAno())){
            Article article = modelMapper.map(articleDTO, Article.class);
            articleRepository.save(article);
        }
    }
    public void remove(int ano){
        articleRepository.deleteById(ano);
    }
}
