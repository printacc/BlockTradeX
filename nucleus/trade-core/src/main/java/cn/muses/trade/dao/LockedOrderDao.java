package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.LockedOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LockedOrderDao extends BaseDao<LockedOrder> {
    List<LockedOrder> findAllByMemberId(Long memberId);

    List<LockedOrder> findAllByMemberIdAndActivityId(Long memberId, Long activityId);

    List<LockedOrder> findAllByActivityId(Long activityId);

    List<LockedOrder> findAllByLockedStatus(int lockedStatus);
}
