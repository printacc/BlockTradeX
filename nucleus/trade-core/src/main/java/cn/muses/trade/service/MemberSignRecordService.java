package cn.muses.trade.service;

import cn.muses.trade.dao.MemberSignRecordDao;
import cn.muses.trade.entity.MemberSignRecord;
import cn.muses.trade.service.Base.TopBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @Description:
 * @date 2019/5/410:19
 */
@Service
public class MemberSignRecordService extends TopBaseService<MemberSignRecord, MemberSignRecordDao> {
    @Override
    @Autowired
    public void setDao(MemberSignRecordDao dao) {
        super.setDao(dao);
    }
}
