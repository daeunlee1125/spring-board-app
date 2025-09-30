package kr.co.sboard.repository.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.entity.QArticle;
import kr.co.sboard.entity.QUser;
import kr.co.sboard.repository.custom.ArticleRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    /*
    반드시 이름을 ArticleRepositoryImpl로(메인 리포지토리 이름으로) 맞춰줘야!
    QueryDSL 생성 에러 발생 방지!!!💥
     */

    private final JPAQueryFactory jpaQueryFactory;

    private QArticle qArticle = QArticle.article;
    private QUser qUser = QUser.user;

    @Override
    public Page<Tuple> selectArticleAllForList(PageRequestDTO pageRequestDTO, Pageable pageable) {

        List<com.querydsl.core.Tuple> tupleList = jpaQueryFactory.select(qArticle, qUser.nick)
                .from(qArticle)
                .join(qUser)
                .on(qArticle.writer.eq(qUser.usid))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qArticle.ano.desc())
                .fetch();

        // 전체 게시물 개수
        long total = jpaQueryFactory.select(qArticle.count()).from(qArticle).fetchOne();

        return new PageImpl<Tuple>(tupleList, PageRequest.of(0, 10), total);
    }

    @Override
    public Page<Tuple> selectArticleAllForSearch(PageRequestDTO pageRequestDTO, Pageable pageable) {

        String searchType = pageRequestDTO.getSearchType();
        String keyword = pageRequestDTO.getKeyword();

        // 검색 타입에 따라 where 조건 표현식 생성 (동적 쿼리)
        BooleanExpression expression = null;

        if (searchType.equals("title")){
            expression = qArticle.title.contains(keyword); // title like %keyword%
        }else if (searchType.equals("writer")){
            // expression = qArticle.writer.contains(keyword);
        }else if (searchType.equals("content")){
            expression = qArticle.content.contains(keyword);
        }

        List<com.querydsl.core.Tuple> tupleList = jpaQueryFactory.select(qArticle, qUser.nick)
                .from(qArticle)
                .join(qUser)
                .on(qArticle.writer.eq(qUser.usid))
                .where(expression)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qArticle.ano.desc())
                .fetch();

        // 전체 게시물 개수
        long total = jpaQueryFactory.select(qArticle.count()).from(qArticle)
                .where(expression)
                .fetchOne();

        return new PageImpl<Tuple>(tupleList, PageRequest.of(0, 10), total);
    }
}
