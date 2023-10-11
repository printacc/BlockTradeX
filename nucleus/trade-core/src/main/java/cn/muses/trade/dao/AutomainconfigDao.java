package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.Automainconfig;
import org.springframework.stereotype.Repository;


@Repository
public interface AutomainconfigDao extends BaseDao<Automainconfig> {

    Automainconfig findFirstByCoinnameAndProtocol(String coinName, Integer protocol);

}
