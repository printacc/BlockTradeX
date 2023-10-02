package cn.muses.trade.dao;

import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.MemberLevel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @description 会员等级Dao
 * @date 2019/12/26 17:24
 */
public interface MemberLevelDao extends BaseDao<MemberLevel> {

    MemberLevel findOneByIsDefault(Boolean isDefault);

    @Query("update MemberLevel set isDefault = false  where isDefault = true ")
    @Modifying
    int updateDefault();
}
