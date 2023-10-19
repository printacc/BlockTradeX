package cn.muses.trade.model.screen;

import cn.muses.trade.constant.BooleanEnum;
import cn.muses.trade.constant.WithdrawStatus;
import lombok.Data;

@Data
public class WithdrawRecordScreen extends AccountScreen{

    private String unit ;

    /**
     * 提现地址
     */
    private String address ;

    private WithdrawStatus status ;

    /**
     * 是否自动提现
     */
    private BooleanEnum isAuto;

    private Long memberId ;

    private String mobilePhone;

    private String orderSn;
}
