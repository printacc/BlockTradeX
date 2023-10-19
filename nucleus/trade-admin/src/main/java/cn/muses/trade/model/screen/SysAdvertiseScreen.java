package cn.muses.trade.model.screen;

import cn.muses.trade.constant.CommonStatus;
import cn.muses.trade.constant.SysAdvertiseLocation;
import lombok.Data;

@Data
public class SysAdvertiseScreen {
    private String serialNumber;
    private SysAdvertiseLocation sysAdvertiseLocation;
    private CommonStatus status;
}
