package cn.muses.trade.dao;

import cn.muses.trade.constant.Platform;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.AppRevision;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @Title: ${file_name}
 * @Description:
 * @date 2019/4/2416:18
 */
public interface AppRevisionDao extends BaseDao<AppRevision> {
    AppRevision findAppRevisionByPlatformOrderByIdDesc(Platform platform);
}
