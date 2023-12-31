package cn.muses.trade.dao;

import cn.muses.trade.entity.ConvertCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


/***
 *
 */
@Repository
public interface ConvertCoinDao extends JpaRepository<ConvertCoin, String>, JpaSpecificationExecutor<ConvertCoin>, QuerydslPredicateExecutor<ConvertCoin> {

    ConvertCoin findByCoinUnit(String coinUnit);

    List<ConvertCoin> findByStatus(Integer status);
}
