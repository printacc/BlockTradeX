package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.PaymentTypeRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentTypeRecordDao extends BaseDao<PaymentTypeRecord> {

    List<PaymentTypeRecord> findAllByMemberId(Long memberId);

}
