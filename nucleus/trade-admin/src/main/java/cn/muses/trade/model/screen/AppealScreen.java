package cn.muses.trade.model.screen;

import cn.muses.trade.constant.AdvertiseType;
import cn.muses.trade.constant.BooleanEnum;
import cn.muses.trade.constant.OrderStatus;
import lombok.Data;

@Data
public class AppealScreen {
    private AdvertiseType advertiseType ;
    private String complainant ;//申诉者
    private String negotiant;//交易者
    private BooleanEnum success;
    private String unit ;
    private OrderStatus status ;
    private Boolean auditing = false ;
}
