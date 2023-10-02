package cn.muses.trade.dao;

import cn.muses.trade.constant.ActivityRewardType;
import cn.muses.trade.constant.BooleanEnum;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.RewardActivitySetting;


public interface RewardActivitySettingDao extends BaseDao<RewardActivitySetting> {
    RewardActivitySetting findByStatusAndType(BooleanEnum booleanEnum, ActivityRewardType type);
}
