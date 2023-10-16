package cn.muses.wallet.rpcclient;

import cn.muses.wallet.rpcclient.Bitcoin.Transaction;

public interface BitcoinPaymentListener {
    void block(String var1);

    void transaction(Transaction var1);
}
