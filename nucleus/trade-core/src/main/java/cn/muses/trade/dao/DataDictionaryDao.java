package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.DataDictionary;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @Title: ${file_name}
 * @Description:
 * @date 2019/4/1214:15
 */
public interface DataDictionaryDao extends BaseDao<DataDictionary> {
    DataDictionary findByBond(String bond);
}
