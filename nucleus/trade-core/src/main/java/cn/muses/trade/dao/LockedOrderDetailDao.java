package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.LockedOrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LockedOrderDetailDao extends BaseDao<LockedOrderDetail> {
    List<LockedOrderDetail> findAllByMemberId(Long memberId);
}
