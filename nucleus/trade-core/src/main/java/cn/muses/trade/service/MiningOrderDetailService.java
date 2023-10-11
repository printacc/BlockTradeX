package cn.muses.trade.service;

import cn.muses.trade.dao.MiningOrderDetailDao;
import cn.muses.trade.entity.MiningOrderDetail;
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

@Service
public class MiningOrderDetailService extends BaseService {
	@Autowired
	private MiningOrderDetailDao miningOrderDetailDao;
	
	public MiningOrderDetail getOne(Long id) {
		return miningOrderDetailDao.getOne(id);
	}
	
    public MiningOrderDetail save(MiningOrderDetail miningOrderDetail) {
        return miningOrderDetailDao.save(miningOrderDetail);
    }

    public MiningOrderDetail saveAndFlush(MiningOrderDetail miningOrderDetail) {
        return miningOrderDetailDao.saveAndFlush(miningOrderDetail);
    }
    
    public Page<MiningOrderDetail> findAll(Predicate predicate, Pageable pageable){
    	return miningOrderDetailDao.findAll(predicate, pageable);
    }
    
    public Page<MiningOrderDetail> findAllByMemberId(Long memberId, int pageNo, int pageSize) {
    	Sort orders = Criteria.sortStatic("createTime.desc");
        //分页参数
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, orders);
        //查询条件
        Criteria<MiningOrderDetail> specification = new Criteria<MiningOrderDetail>();
        specification.add(Restrictions.eq("memberId", memberId, false));
        
        return miningOrderDetailDao.findAll(specification, pageRequest);
    }
    
    public Page<MiningOrderDetail> findAllByMiningOrderId(Long miningOrderId, int pageNo, int pageSize) {
    	Sort orders = Criteria.sortStatic("createTime.desc");
        //分页参数
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, orders);
        //查询条件
        Criteria<MiningOrderDetail> specification = new Criteria<MiningOrderDetail>();
        specification.add(Restrictions.eq("miningOrderId", miningOrderId, false));
        
        return miningOrderDetailDao.findAll(specification, pageRequest);
    }
}
