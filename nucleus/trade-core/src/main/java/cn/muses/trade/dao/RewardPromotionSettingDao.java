package cn.muses.trade.dao;

import cn.muses.trade.constant.BooleanEnum;
import cn.muses.trade.constant.PromotionRewardType;
import cn.muses.trade.dao.base.BaseDao;
import cn.muses.trade.entity.RewardPromotionSetting;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年03月08日
 */
public interface RewardPromotionSettingDao extends BaseDao<RewardPromotionSetting> {
    RewardPromotionSetting findByStatusAndType(BooleanEnum booleanEnum, PromotionRewardType type);
}
