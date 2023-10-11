package cn.muses.trade.dao;

import cn.muses.trade.entity.ConvertOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


/***
 *
 */
@Repository
public interface ConvertOrderDao extends JpaRepository<ConvertOrder, String>, JpaSpecificationExecutor<ConvertOrder>, QuerydslPredicateExecutor<ConvertOrder> {

}
