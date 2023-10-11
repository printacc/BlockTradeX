package cn.muses.trade.service;

import cn.muses.trade.dao.RedEnvelopeDao;
import cn.muses.trade.dao.RedEnvelopeDetailDao;
import cn.muses.trade.entity.RedEnvelope;
import cn.muses.trade.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedEnvelopeService extends BaseService {
	
	@Autowired
	private RedEnvelopeDao redEnvelopeDao;
	
	@Autowired
	private RedEnvelopeDetailDao redEnvelopeDetailDao;
	
	public RedEnvelope findByEnvelopeNo(String envelopeNo) {
		return redEnvelopeDao.findByEnvelopeNo(envelopeNo);
	}

	public List<RedEnvelope> findAllByMemberId(Long memberId){
		return redEnvelopeDao.findAllByMemberId(memberId);
	}
	public List<RedEnvelope> findAllByState(int state){
		return redEnvelopeDao.findAllByState(state);
	}
//	public RedEnvelope getOne(Long id) {
//		return redEnvelopeDao.getOne(id);
//	}
	
    public RedEnvelope save(RedEnvelope envelope) {
        return redEnvelopeDao.save(envelope);
    }

    public RedEnvelope saveAndFlush(RedEnvelope envelope) {
        return redEnvelopeDao.saveAndFlush(envelope);
    }
    
//    public RedEnvelope findById(Long id) {
//        return redEnvelopeDao.getOne(id);
//    }
    
//    public Page<RedEnvelope> findAll(Predicate predicate, Pageable pageable){
//    	return redEnvelopeDao.findAll(predicate, pageable);
//    }
    
//    public Page<RedEnvelope> findByMember(Long memberId, int pageNo, int pageSize){
//        Sort orders = Criteria.sortStatic("createTime.desc");
//        //分页参数
//        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, orders);
//        //查询条件
//        Criteria<RedEnvelope> specification = new Criteria<RedEnvelope>();
//        specification.add(Restrictions.eq("memberId", memberId, false));
//        return redEnvelopeDao.findAll(specification, pageRequest);
//    }
}
