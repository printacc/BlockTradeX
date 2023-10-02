package cn.muses.trade.dao;

import cn.muses.trade.constant.RewardRecordType;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.Member;
import cn.muses.trade.entity.RewardRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RewardRecordDao extends BaseDao<RewardRecord> {
    List<RewardRecord> findAllByMemberAndType(Member member, RewardRecordType type);

    @Query(value = "select coin_id , sum(amount) from reward_record where member_id = :memberId and type = :type group by coin_id",nativeQuery = true)
    List<Object[]> getAllPromotionReward(@Param("memberId") long memberId ,@Param("type") int type);
}
