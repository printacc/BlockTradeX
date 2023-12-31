package cn.muses.trade.dao;

import cn.muses.trade.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @description 货币操作
 * @date 2021/12/29 14:41
 */
@Repository
public interface CurrencyDao extends JpaRepository<Currency, Long>, JpaSpecificationExecutor<Currency>, QuerydslPredicateExecutor<Currency> {

}
