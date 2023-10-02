package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.Activity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityDao extends  BaseDao<Activity> {
	
    List<Activity> findAllByStep(int step);

    List<Activity> findAllByTypeAndStep(int type, int step);
}
