package cn.muses.trade.model.screen;

import cn.muses.trade.constant.WithdrawStatus;
import lombok.Data;

@Data
public class LegalWalletWithdrawScreen {
    WithdrawStatus status;
    String username;
    String coinName;

}
