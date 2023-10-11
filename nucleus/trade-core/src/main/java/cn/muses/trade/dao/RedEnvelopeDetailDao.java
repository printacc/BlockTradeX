package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.RedEnvelopeDetail;

import java.util.List;

public interface RedEnvelopeDetailDao  extends BaseDao<RedEnvelopeDetail>{
	
	List<RedEnvelopeDetail> findAllByEnvelopeIdAndMemberId(Long envelopeId, Long memberId);
	
	List<RedEnvelopeDetail> findAllByEnvelopeId(Long envelopeId);
}
