package cn.muses.trade.service;

import cn.muses.trade.dao.TransferRecordDao;
import cn.muses.trade.entity.TransferRecord;
import cn.muses.trade.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年02月27日
 */
@Service
public class TransferRecordService extends BaseService {
    @Autowired
    private TransferRecordDao transferRecordDao;

    public TransferRecord save(TransferRecord transferRecord){
        return transferRecordDao.save(transferRecord);
    }

//    @Transactional(readOnly = true)
//    public Page<TransferRecord> findAllByMemberId(Long memberId, int page, int pageSize) {
//        Sort orders = Criteria.sortStatic("id.desc");
//        PageRequest pageRequest = PageRequest.of(page, pageSize, orders);
//        Criteria<TransferRecord> specification = new Criteria<>();
//        specification.add(Restrictions.eq("memberId", memberId, false));
//        return transferRecordDao.findAll(specification, pageRequest);
//    }
}
