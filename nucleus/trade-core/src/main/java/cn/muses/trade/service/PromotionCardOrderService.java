package cn.muses.trade.service;

import cn.muses.trade.dao.PromotionCardOrderDao;
import cn.muses.trade.entity.PromotionCardOrder;
import cn.muses.trade.service.Base.BaseService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionCardOrderService extends BaseService {
	
	@Autowired
	private PromotionCardOrderDao promotionCardOrderDao;

	public List<PromotionCardOrder> findByCardIdAndMemberId(Long cardId, Long memberId){
		return promotionCardOrderDao.findAllByCardIdAndMemberId(cardId, memberId);
	}
	
	public List<PromotionCardOrder> findAllByCardId(Long cardId) {
		return promotionCardOrderDao.findAllByCardId(cardId);
	}
	
	public PromotionCardOrder getOne(Long id) {
		return promotionCardOrderDao.getOne(id);
	}
	
    public PromotionCardOrder save(PromotionCardOrder order) {
        return promotionCardOrderDao.save(order);
    }

    public PromotionCardOrder saveAndFlush(PromotionCardOrder order) {
        return promotionCardOrderDao.saveAndFlush(order);
    }
    
    public PromotionCardOrder findById(Long id) {
        return promotionCardOrderDao.getOne(id);
    }
    
    public Page<PromotionCardOrder> findAll(Predicate predicate, Pageable pageable){
    	return promotionCardOrderDao.findAll(predicate, pageable);
    }

	public List<PromotionCardOrder> findAllByMemberIdAndIsFree(long memberId, int isFree) {
		return promotionCardOrderDao.findAllByMemberIdAndIsFree(memberId, isFree);
	}
}
