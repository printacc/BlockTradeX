package cn.muses.trade.service;

import cn.muses.trade.dao.PromotionCardDao;
import cn.muses.trade.dao.PromotionCardOrderDao;
import cn.muses.trade.entity.PromotionCard;
import cn.muses.trade.service.Base.BaseService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionCardService extends BaseService {
	@Autowired
	private PromotionCardDao promotionCardDao;
	
	@Autowired
	private PromotionCardOrderDao promotionCardOrderDao;
	
	public PromotionCard findPromotionCardByCardNo(String cardNo) {
		return promotionCardDao.findByCardNo(cardNo);
	}

	public List<PromotionCard> findAllByMemberId(Long memberId){
		return promotionCardDao.findAllByMemberId(memberId);
	}
	
	public PromotionCard getOne(Long id) {
		return promotionCardDao.getOne(id);
	}
	
    public PromotionCard save(PromotionCard card) {
        return promotionCardDao.save(card);
    }

    public PromotionCard saveAndFlush(PromotionCard card) {
        return promotionCardDao.saveAndFlush(card);
    }
    
    public PromotionCard findById(Long id) {
        return promotionCardDao.getOne(id);
    }
    
    public Page<PromotionCard> findAll(Predicate predicate, Pageable pageable){
    	return promotionCardDao.findAll(predicate, pageable);
    }

	public List<PromotionCard> findAllByMemberIdAndIsFree(long memberId, int isFree) {
		return promotionCardDao.findAllByMemberIdAndIsFree(memberId, isFree);
	}
}
