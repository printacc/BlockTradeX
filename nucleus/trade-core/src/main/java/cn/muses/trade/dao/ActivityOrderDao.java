package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.ActivityOrder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ActivityOrderDao extends BaseDao<ActivityOrder> {
	
	List<ActivityOrder> getAllByActivityIdEquals(Long activityId);
	List<ActivityOrder> getAllByMemberIdAndActivityIdEquals(Long memberId, Long activityId);
	
}
