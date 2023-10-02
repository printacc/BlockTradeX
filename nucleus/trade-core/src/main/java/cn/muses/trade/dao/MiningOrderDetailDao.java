package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.MiningOrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiningOrderDetailDao  extends BaseDao<MiningOrderDetail> {
	
	List<MiningOrderDetail> findAllByMemberId(Long memberId);
	
}
