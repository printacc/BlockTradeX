package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.PromotionCardOrder;

import java.util.List;

public interface PromotionCardOrderDao extends BaseDao<PromotionCardOrder> {
	List<PromotionCardOrder> findAllByCardIdAndMemberId(Long cardId, Long memberId);
	
	List<PromotionCardOrder> findAllByCardId(Long cardId);


	List<PromotionCardOrder> findAllByMemberIdAndIsFree(long memberId, int isFree);
}
