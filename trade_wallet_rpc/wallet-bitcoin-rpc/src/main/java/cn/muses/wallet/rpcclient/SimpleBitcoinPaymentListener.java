package cn.muses.wallet.rpcclient;

import cn.muses.wallet.rpcclient.Bitcoin.Transaction;

public class SimpleBitcoinPaymentListener implements BitcoinPaymentListener {
    public SimpleBitcoinPaymentListener() {
    }

    public void block(String blockHash) {
    }

    public void transaction(Transaction transaction) {
    }
}
