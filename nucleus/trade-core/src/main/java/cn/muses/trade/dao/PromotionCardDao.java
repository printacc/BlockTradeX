package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.PromotionCard;

import java.util.List;

public interface PromotionCardDao extends BaseDao<PromotionCard> {
	
	PromotionCard findByCardNo(String cardNo);
	
	List<PromotionCard> findAllByMemberId(Long memberId);

	List<PromotionCard> findAllByMemberIdAndIsFree(long memberId, int isFree);
}
