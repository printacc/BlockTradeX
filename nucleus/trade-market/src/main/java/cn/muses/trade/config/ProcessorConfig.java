package cn.muses.trade.config;

import cn.muses.trade.component.CoinExchangeRate;
import cn.muses.trade.entity.ExchangeCoin;
import cn.muses.trade.handler.MongoMarketHandler;
import cn.muses.trade.handler.NettyHandler;
import cn.muses.trade.handler.WebsocketMarketHandler;
import cn.muses.trade.processor.CoinProcessor;
import cn.muses.trade.processor.CoinProcessorFactory;
import cn.muses.trade.processor.DefaultCoinProcessor;
import cn.muses.trade.service.ExchangeCoinService;
import cn.muses.trade.service.MarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@Slf4j
public class ProcessorConfig {

    @Bean
    public CoinProcessorFactory processorFactory(MongoMarketHandler mongoMarketHandler,
                                                 WebsocketMarketHandler wsHandler,
                                                 NettyHandler nettyHandler,
                                                 MarketService marketService,
                                                 CoinExchangeRate exchangeRate,
                                                 ExchangeCoinService coinService,
                                                 RestTemplate restTemplate) {

        log.info("====initialized CoinProcessorFactory start==================================");

        CoinProcessorFactory factory = new CoinProcessorFactory();
        List<ExchangeCoin> coins = coinService.findAllEnabled();
        log.info("exchange-coin result:{}",coins);
        
        for (ExchangeCoin coin : coins) {
            CoinProcessor processor = new DefaultCoinProcessor(coin.getSymbol(), coin.getBaseSymbol());
            processor.addHandler(mongoMarketHandler);
            processor.addHandler(wsHandler);
            processor.addHandler(nettyHandler);
            processor.setMarketService(marketService);
            processor.setExchangeRate(exchangeRate);
            processor.setIsStopKLine(true);
            
            factory.addProcessor(coin.getSymbol(), processor);
            log.info("new processor = ", processor);
        }

        log.info("====initialized CoinProcessorFactory completed====");
        log.info("CoinProcessorFactory = ", factory);
        exchangeRate.setCoinProcessorFactory(factory);
        return factory;
    }
}
