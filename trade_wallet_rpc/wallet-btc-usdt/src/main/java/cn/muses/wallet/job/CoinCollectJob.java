package cn.muses.wallet.job;

import cn.muses.wallet.config.JsonrpcClient;
import cn.muses.wallet.entity.Coin;
import cn.muses.wallet.service.AccountService;
import cn.muses.wallet.util.AccountReplay;
import cn.muses.wallet.rpcclient.BitcoinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CoinCollectJob {
    private final Logger logger = LoggerFactory.getLogger(CoinCollectJob.class);
    @Autowired
    private AccountService accountService;
    @Autowired
    private JsonrpcClient rpcClient;
    @Autowired
    private Coin coin;

    /**
     * 每两小时检查一次
     */
    @Scheduled(cron = "0 0 */2 * * ?")
    public void rechargeMinerFee() {
        try {
            AccountReplay accountReplay = new AccountReplay(accountService, 100);
            accountReplay.run(account -> {
                BigDecimal btcBalance = rpcClient.getAddressBalance(account.getAddress());
                if (btcBalance.compareTo(coin.getRechargeMinerFee()) < 0) {
                    BigDecimal usdtBalance = rpcClient.omniGetBalance(account.getAddress());
                    if (usdtBalance.compareTo(coin.getMinCollectAmount()) >= 0) {
                        try {
                            String txid = BitcoinUtil.sendTransaction(rpcClient, coin.getWithdrawAddress(), account.getAddress(), coin.getRechargeMinerFee(), coin.getDefaultMinerFee());
                            logger.info("BitcoinUtil.sendTransaction:address={},txid={}", account.getAddress(), txid);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
