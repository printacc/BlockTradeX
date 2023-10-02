package cn.muses.trade.dao;

import cn.muses.trade.constant.CommonStatus;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.BusinessAuthDeposit;

import java.util.List;


public interface BusinessAuthDepositDao extends BaseDao<BusinessAuthDeposit> {
    public List<BusinessAuthDeposit> findAllByStatus(CommonStatus status);
}
