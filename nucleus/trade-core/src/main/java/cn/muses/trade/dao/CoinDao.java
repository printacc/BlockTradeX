package cn.muses.trade.dao;

import cn.muses.trade.constant.BooleanEnum;
import cn.muses.trade.constant.CommonStatus;
import cn.muses.trade.dto.CoinDTO;
import cn.muses.trade.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @description 货币操作
 * @date 2021/12/29 14:41
 */
@Repository
public interface CoinDao extends JpaRepository<Coin, String>, JpaSpecificationExecutor<Coin>, QuerydslPredicateExecutor<Coin> {
    Coin findByUnit(String unit);

    List<Coin> findAllByCanWithdrawAndStatusAndHasLegal(BooleanEnum is, CommonStatus status, boolean hasLegal);

    List<Coin> findAllByStatusAndHasLegal(CommonStatus status, boolean hasLegal);

    Coin findCoinByIsPlatformCoin(BooleanEnum is);

    List<Coin> findByHasLegal(Boolean hasLegal);

    @Query("select a from Coin a where a.unit in (:units) ")
    List<Coin> findAllByOtc(@Param("units") List<String> otcUnits);

    @Query("select a.name from Coin a")
    List<String> findAllName();

    @Query(value = "select  new cn.muses.trade.dto.CoinDTO(a.name,a.unit) from Coin a")
    List<CoinDTO> findAllNameAndUnit();

    @Query("select a.name from Coin a where a.hasLegal = true ")
    List<String> findAllCoinNameLegal();

    @Query("select a.unit from Coin a where a.enableRpc = 1")
    List<String> findAllRpcUnit();

    List<Coin> findAllByIsPlatformCoin(BooleanEnum isPlatformCoin);

    @Query("select new cn.muses.trade.dto.CoinDTO(a.name,a.unit) from Coin a where a.status = 0 order by a.sort ASC")
    List<CoinDTO> list();

    @Query(value = "select c.CoinName,c.Ext,c.Decimals,t.MinNum from coinext c LEFT JOIN coinprotocol p on p.Protocol=c.Protocol left join automainconfig t on t.CoinName=c.CoinName and t.Protocol=p.Protocol where p.Symbol = :protocol and c.Ext != ''",nativeQuery = true)
    List<Object[]> getContractByProtocol(@Param("protocol")String protocol);
}
