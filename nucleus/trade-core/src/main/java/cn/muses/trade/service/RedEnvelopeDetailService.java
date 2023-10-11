package cn.muses.trade.service;

import cn.muses.trade.dao.RedEnvelopeDetailDao;
import cn.muses.trade.entity.RedEnvelopeDetail;
import cn.muses.trade.pagination.Criteria;
import cn.muses.trade.pagination.Restrictions;
import cn.muses.trade.service.Base.BaseService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedEnvelopeDetailService extends BaseService{
	@Autowired
	private RedEnvelopeDetailDao redEnvelopeDetailDao;
	

	public List<RedEnvelopeDetail> findByEnvelopeIdAndMemberId(Long envelopeId, Long memberId){
		return redEnvelopeDetailDao.findAllByEnvelopeIdAndMemberId(envelopeId, memberId);
	}
	
	public List<RedEnvelopeDetail> findAllByEnvelopeId(Long envelopeId) {
		return redEnvelopeDetailDao.findAllByEnvelopeId(envelopeId);
	}
	
	public RedEnvelopeDetail getOne(Long id) {
		return redEnvelopeDetailDao.getOne(id);
	}
	
    public RedEnvelopeDetail save(RedEnvelopeDetail detail) {
        return redEnvelopeDetailDao.save(detail);
    }

    public RedEnvelopeDetail saveAndFlush(RedEnvelopeDetail detail) {
        return redEnvelopeDetailDao.saveAndFlush(detail);
    }
    
    public RedEnvelopeDetail findById(Long id) {
        return redEnvelopeDetailDao.getOne(id);
    }
    
    public Page<RedEnvelopeDetail> findAll(Predicate predicate, Pageable pageable){
    	return redEnvelopeDetailDao.findAll(predicate, pageable);
    }
    
    public Page<RedEnvelopeDetail> findByEnvelope(Long envelopeId, int pageNo, int pageSize){
    	//排序方式 (需要倒序 这样    Criteria.sort("id","createTime.desc") ) //参数实体类为字段名
        Sort orders = Criteria.sortStatic("createTime.desc");
        //分页参数
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, orders);
        //查询条件
        Criteria<RedEnvelopeDetail> specification = new Criteria<RedEnvelopeDetail>();
        specification.add(Restrictions.eq("envelopeId", envelopeId, false));
        return redEnvelopeDetailDao.findAll(specification, pageRequest);
    }
}