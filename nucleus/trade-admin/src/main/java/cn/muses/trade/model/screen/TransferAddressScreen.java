package cn.muses.trade.model.screen;

import cn.muses.trade.constant.CommonStatus;
import lombok.Data;

@Data
public class TransferAddressScreen {
    private CommonStatus start ;
    private String address;
    private String unit;
}
