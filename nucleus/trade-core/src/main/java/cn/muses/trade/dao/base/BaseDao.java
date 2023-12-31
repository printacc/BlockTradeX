package cn.muses.trade.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @description
 * @date 2019/1/18 10:38
 */
@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T,Long>,JpaSpecificationExecutor<T>,QuerydslPredicateExecutor<T> {
}
