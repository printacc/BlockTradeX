package cn.muses.trade.dao;

import cn.muses.trade.constant.CommonStatus;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.BusinessAuthDeposit;

import java.util.List;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2019/5/5
 */
public interface BusinessAuthDepositDao extends BaseDao<BusinessAuthDeposit> {
    public List<BusinessAuthDeposit> findAllByStatus(CommonStatus status);
}
