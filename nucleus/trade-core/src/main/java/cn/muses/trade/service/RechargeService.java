package cn.muses.trade.service;

import cn.muses.trade.entity.Recharge;
import cn.muses.trade.service.Base.BaseService;
import org.springframework.stereotype.Service;


@Service
public class RechargeService extends BaseService<Recharge> {

//    @Autowired
//    private RechargeDao rechargeDao;
//
//    public Page<Recharge> findAllByMemberId(Integer memberId, int page, int pageSize) {
//        Sort orders = Criteria.sortStatic("id.desc");
//        PageRequest pageRequest = PageRequest.of(page, pageSize, orders);
//        Criteria<Recharge> specification = new Criteria<>();
//        specification.add(Restrictions.eq("memberid", memberId, false));
//        return rechargeDao.findAll(specification, pageRequest);
//    }
//
//
//    public Page<Recharge> findAll(Predicate predicate, Pageable pageable) {
//        return rechargeDao.findAll(predicate, pageable);
//    }
//
//    public Recharge save(Recharge recharge){
//        return rechargeDao.save(recharge);
//    }
//
//
//    public Iterable<Recharge> findAllOut(Predicate predicate) {
//        return rechargeDao.findAll(predicate, Sort.by(Sort.Direction.DESC, "id"));
//    }

}
