package cn.muses.wallet.event;

import cn.muses.wallet.component.Watcher;
import cn.muses.wallet.entity.Coin;
import cn.muses.wallet.entity.WatcherLog;
import cn.muses.wallet.entity.WatcherSetting;
import cn.muses.wallet.service.WatcherLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
public class ApplicationEvent implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LoggerFactory.getLogger(ApplicationEvent.class);
    @Autowired
    private DepositEvent depositEvent;
    @Autowired(required = false)
    private Watcher watcher;
    @Autowired
    private Coin coin;
    @Autowired
    private WatcherLogService watcherLogService;
    @Autowired
    private WatcherSetting watcherSetting;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (watcher != null) {
            logger.info("=======Initialize Block Data Watcher=====");
            WatcherLog watcherLog = watcherLogService.findOne(coin.getName());
            logger.info("watcherLog:{}", watcherLog);
            if (watcherLog != null) {
                watcher.setCurrentBlockHeight(watcherLog.getLastSyncHeight());
            } else if (watcherSetting.getInitBlockHeight().equalsIgnoreCase("latest")) {
                watcher.setCurrentBlockHeight(watcher.getNetworkBlockHeight());
            } else {
                Long height = Long.parseLong(watcherSetting.getInitBlockHeight());
                watcher.setCurrentBlockHeight(height);
            }
            //初始化参数
            //设置每次同步区块数量
            watcher.setStep(watcherSetting.getStep());
            //设置任务执行间隔
            watcher.setCheckInterval(watcherSetting.getInterval());
            watcher.setDepositEvent(depositEvent);
            //设置币种配置信息
            watcher.setCoin(coin);
            watcher.setWatcherLogService(watcherLogService);
            //设置交易需要的确认数
            watcher.setConfirmation(watcherSetting.getConfirmation());
            new Thread(watcher).start();
        } else {
            logger.error("=====启动程序失败=====");
        }
    }
}
