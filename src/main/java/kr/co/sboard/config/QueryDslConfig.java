package kr.co.sboard.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDslConfig {

    @PersistenceContext // 엔티티매니저 주입받는 어노테이션
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory  jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }

}
