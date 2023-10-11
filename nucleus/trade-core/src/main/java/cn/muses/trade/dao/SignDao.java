package cn.muses.trade.dao;

import cn.muses.trade.constant.SignStatus;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.Sign;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @Description:
 * @date 2019/5/311:10
 */
public interface SignDao extends BaseDao<Sign> {
    Sign findByStatus(SignStatus status);
}
