package cn.muses.trade.config;

import cn.muses.trade.client.Client;
import cn.muses.trade.service.KlineRobotMarketService;
import cn.muses.trade.socket.client.WsClientHuobi;
import cn.muses.trade.util.WebSocketConnectionManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContractCoinMatchStarter implements ApplicationRunner {

    private Logger log = LoggerFactory.getLogger(ContractCoinMatchStarter.class);

    @Autowired
    private Client client;


    @Autowired
    private KlineRobotMarketService klineRobotMarketService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        try {
            WebSocketConnectionManage.setClient(client);

            WsClientHuobi w = new WsClientHuobi();
            w.setContractMarketService(klineRobotMarketService);
            w.run();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("！！！！！！！");
        }

    }
}
