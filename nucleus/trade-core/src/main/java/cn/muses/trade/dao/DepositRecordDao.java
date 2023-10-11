package cn.muses.trade.dao;

import cn.muses.trade.constant.DepositStatusEnum;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.DepositRecord;
import cn.muses.trade.entity.Member;

import java.util.List;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2019/5/7
 */
public interface DepositRecordDao extends BaseDao<DepositRecord> {
    public DepositRecord findById(String id);

    public List<DepositRecord> findByMemberAndStatus(Member member, DepositStatusEnum status);
}
