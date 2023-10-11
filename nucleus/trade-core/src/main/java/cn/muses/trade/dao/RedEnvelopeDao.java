package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.RedEnvelope;

import java.util.List;

public interface RedEnvelopeDao extends BaseDao<RedEnvelope>{
	
	RedEnvelope findByEnvelopeNo(String envelopeNo);
	
	List<RedEnvelope> findAllByMemberId(Long memberId);
	
	List<RedEnvelope> findAllByState(int state);
}
