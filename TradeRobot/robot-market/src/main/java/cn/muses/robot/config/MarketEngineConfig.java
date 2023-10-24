package cn.muses.robot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.muses.robot.engine.MarketEngine;

import cn.muses.robot.engine.MarketEngineFactory;
import cn.muses.robot.engine.MarketEngineHuobi;
import cn.muses.robot.engine.MarketEngineOkex;
import cn.muses.robot.engine.MarketEngineBinance;
import cn.muses.robot.engine.MarketEngineZb;
import cn.muses.robot.engine.MarketEngineFxh;
import cn.muses.robot.engine.MarketEngineBiki;

@Configuration
public class MarketEngineConfig {
    @Bean
    public MarketEngineFactory marketEngineFactory() {

        MarketEngineFactory factory = new MarketEngineFactory();

        MarketEngine huobiEngine = new MarketEngineHuobi();
        factory.addEngine("Huobi", huobiEngine);

        MarketEngine okexEngine = new MarketEngineOkex();
        factory.addEngine("Okex", okexEngine);

        MarketEngine binanceEngine = new MarketEngineBinance();
        factory.addEngine("Binance", binanceEngine);

		MarketEngine zbEngine = new MarketEngineZb();
		factory.addEngine("Zb", zbEngine);

//		MarketEngine bikiEngine = new MarketEngineBiki();
//		factory.addEngine("Biki", bikiEngine);

//		MarketEngine fxhEngine = new MarketEngineFxh();
//		factory.addEngine("Fxh", fxhEngine);

        return factory;
    }
}
