package cn.muses.trade.dao;


import cn.muses.trade.constant.TransactionType;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.MemberTransaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MemberTransactionDao extends BaseDao<MemberTransaction> {

    @Query("select m from MemberTransaction as m where m.createTime between :startTime and  :endTime and m.type = :type")
    List<MemberTransaction> findAllDailyMatch(String startTime,String endTime,TransactionType type);

    /*@Query(value="select sum(amount),sum(fee) ,symbol as unit from member_transaction where type = 3 and date_format(create_time,'%Y-%m-%d')= :date group by symbol",nativeQuery = true)
    List<Object[]> getExchangeTurnover(@Param("date") String date);*/

    @Query("select sum(t.amount)  as amount from MemberTransaction t where t.flag = 0 and t.amount > 0 and t.memberId = :memberId and t.symbol = :symbol and t.type in :types")
    Map<String,Object> findMatchTransactionSum(@Param("memberId") Long memberId,@Param("symbol") String symbol,@Param("types") List<TransactionType> types);

    @Query("select sum(t.amount)  as amount from MemberTransaction t where t.flag = 0  and t.memberId = :memberId and t.symbol = :symbol and t.type = :type and t.createTime >= :startTime and t.createTime <= :endTime")
    Map<String,Object> findMatchTransactionSum(@Param("memberId") Long memberId,@Param("symbol") String symbol,@Param("type") TransactionType type,@Param("startTime") Date startTime,@Param("endTime") Date endTime);

    @Query("select sum(t.amount)  as amount from MemberTransaction t where t.flag = 0  and t.symbol = :symbol and t.type = :type and t.createTime >= :startTime and t.createTime <= :endTime")
    Map<String,Object> findMatchTransactionSum(@Param("symbol") String symbol,@Param("type") TransactionType type,@Param("startTime") Date startTime,@Param("endTime") Date endTime);

    @Transactional
    @Modifying
    @Query("delete from MemberTransaction as m where m.createTime < :beforeTime and m.type = 3 and m.memberId = 1")
    int deleteHistory(@Param("beforeTime") Date beforeTime);

    @Transactional
    @Modifying
    @Query(value = "delete from member_wallet_history where op_time < :beforeTime and member_id = 1", nativeQuery = true)
    int deleteWalletHistory(@Param("beforeTime") Date beforeTime);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update MemberTransaction o set o.isReward = :isReward where o.id = :id")
    int updateReward(@Param("id") long id, @Param("isReward") int isReward);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update MemberTransaction o set o.isReward = 1 where o.memberId = 1")
    void updateRewardRobot();
}
