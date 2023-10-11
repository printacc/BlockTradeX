package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.MiningOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiningOrderDao extends BaseDao<MiningOrder> {

	List<MiningOrder> findAllByMemberId(Long memberId);
	
	List<MiningOrder> findAllByMemberIdAndActivityId(Long memberId, Long activityId);
	
	List<MiningOrder> findAllByActivityId(Long activityId);
	
	List<MiningOrder> findAllByMiningStatus(int miningStatus);
}
