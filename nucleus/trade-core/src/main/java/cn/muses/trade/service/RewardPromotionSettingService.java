package cn.muses.trade.service;

import cn.muses.trade.constant.BooleanEnum;
import cn.muses.trade.constant.PromotionRewardType;
import cn.muses.trade.dao.RewardPromotionSettingDao;
import cn.muses.trade.entity.RewardPromotionSetting;
import cn.muses.trade.service.Base.TopBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年03月08日
 */
@Service
public class RewardPromotionSettingService  extends TopBaseService<RewardPromotionSetting,RewardPromotionSettingDao> {

    @Override
    @Autowired
    public void setDao(RewardPromotionSettingDao dao) {
        super.setDao(dao);
    }

    public RewardPromotionSetting findByType(PromotionRewardType type){
        return dao.findByStatusAndType(BooleanEnum.IS_TRUE, type);
    }

    @Override
    public RewardPromotionSetting save(RewardPromotionSetting setting){
        return dao.save(setting);
    }

    public void deletes(long[] ids){
        for(long id : ids){
            delete(id);
        }
    }

}
