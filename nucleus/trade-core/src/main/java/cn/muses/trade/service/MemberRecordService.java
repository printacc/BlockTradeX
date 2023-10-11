package cn.muses.trade.service;

import cn.muses.trade.dao.MemberRecordDao;
import cn.muses.trade.entity.MemberRecord;
import cn.muses.trade.service.Base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: bigdogex.com
 * @Date: 2021-01-20 12:31
 */
@Service
public class MemberRecordService extends BaseService {
    @Autowired
    private MemberRecordDao memberRecordDao;

    public MemberRecord save(MemberRecord memberRecord) {
        return memberRecordDao.save(memberRecord);
    }
    public List<MemberRecord> findALlByAnalysised(int analysised){
        return memberRecordDao.findAllByAnalysised(analysised);
    }
}
