package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.MemberRecord;

import java.util.List;

/**
 * @Author: bigdogex.com
 * @Date: 2021-01-20 12:30
 */
public interface MemberRecordDao extends BaseDao<MemberRecord> {
    List<MemberRecord> findAllByAnalysised(int analysised);
}
