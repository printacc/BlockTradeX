package cn.muses.trade.model.screen;

import cn.muses.trade.constant.AdvertiseControlStatus;
import cn.muses.trade.constant.AdvertiseType;
import lombok.Data;

@Data
public class AdvertiseScreen extends AccountScreen{

    AdvertiseType advertiseType;

    String payModel ;

    /**
     * 广告状态 (012  上架/下架/关闭)
     */
    AdvertiseControlStatus status ;

}
