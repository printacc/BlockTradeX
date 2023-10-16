package cn.muses.wallet.util;

import cn.muses.wallet.entity.Account;

public interface AccountReplayListener {

    void replay(Account account);
}
