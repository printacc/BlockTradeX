package com.muses.er.market.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.muses.er.market.engine.MarketEngine;

import com.muses.er.market.engine.MarketEngineFactory;
import com.muses.er.market.engine.MarketEngineHuobi;
import com.muses.er.market.engine.MarketEngineOkex;
import com.muses.er.market.engine.MarketEngineBinance;
import com.muses.er.market.engine.MarketEngineZb;

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
