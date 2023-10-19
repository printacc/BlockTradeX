package cn.muses.trade.model.screen;

import cn.muses.trade.constant.LegalWalletState;
import lombok.Data;

@Data
public class LegalWalletRechargeScreen {
    LegalWalletState status;
    String username;
    String coinName;

}
