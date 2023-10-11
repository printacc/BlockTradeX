package cn.muses.trade.dao;

import cn.muses.trade.constant.ActivityRewardType;
import cn.muses.trade.constant.BooleanEnum;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.RewardActivitySetting;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年03月08日
 */
public interface RewardActivitySettingDao extends BaseDao<RewardActivitySetting> {
    RewardActivitySetting findByStatusAndType(BooleanEnum booleanEnum, ActivityRewardType type);
}
