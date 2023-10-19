package cn.muses.trade.service;

import cn.muses.trade.dao.RechargeDao;
import cn.muses.trade.entity.Recharge;
import cn.muses.trade.pagination.Criteria;
import cn.muses.trade.pagination.Restrictions;
import cn.muses.trade.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;


@Service
public class RechargeService extends BaseService<Recharge> {

    @Autowired
    private RechargeDao rechargeDao;

    public Page<Recharge> findAllByMemberId(Integer memberId, int page, int pageSize) {
        Sort orders = Criteria.sortStatic("id.desc");
        PageRequest pageRequest = PageRequest.of(page, pageSize, orders);
        Criteria<Recharge> specification = new Criteria<>();
        specification.add(Restrictions.eq("memberid", memberId, false));
        return rechargeDao.findAll(specification, pageRequest);
    }


    public Page<Recharge> findAll(Predicate predicate, Pageable pageable) {
//        return rechargeDao.findAll(predicate, pageable);
        return null;
    }

    public Recharge save(Recharge recharge){
        return rechargeDao.save(recharge);
    }
//
//
    public Iterable<Recharge> findAllOut(Predicate predicate) {
        return rechargeDao.findAll((com.querydsl.core.types.Predicate) predicate, Sort.by(Sort.Direction.DESC, "id"));
    }

}
