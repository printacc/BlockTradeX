package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.QuickExchange;

import java.util.List;

public interface QuickExchangeDao extends BaseDao<QuickExchange> {
    List<QuickExchange> findAllByMemberId(Long memberId);
}
