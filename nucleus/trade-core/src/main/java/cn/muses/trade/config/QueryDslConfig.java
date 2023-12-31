package cn.muses.trade.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @description
 * @date 2019/1/18 11:29
 */
@Configuration
public class QueryDslConfig {
    @Bean
    public JPAQueryFactory getJPAQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }
}
