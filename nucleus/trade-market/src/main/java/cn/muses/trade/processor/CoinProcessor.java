package cn.muses.trade.processor;

import cn.muses.trade.component.CoinExchangeRate;
import cn.muses.trade.entity.CoinThumb;
import cn.muses.trade.entity.ExchangeTrade;
import cn.muses.trade.entity.KLine;
import cn.muses.trade.handler.MarketHandler;
import cn.muses.trade.service.MarketService;

import java.util.List;

public interface CoinProcessor {

    void setIsHalt(boolean status);

    void setIsStopKLine(boolean stop);
    
    boolean isStopKline();
    /**
     * 处理新生成的交易信息
     * @param trades
     * @return
     */
    void process(List<ExchangeTrade> trades);

    /**
     * 添加存储器
     * @param storage
     */
    void addHandler(MarketHandler storage);

    CoinThumb getThumb();

    void setMarketService(MarketService service);

    void generateKLine(int range, int field, long time);

    void generateKLine1min(int range, int field, long time);

    KLine getKLine();

    void initializeThumb();

    void autoGenerate();

    void resetThumb();

    void setExchangeRate(CoinExchangeRate coinExchangeRate);

    void update24HVolume(long time);

    void initializeUsdRate();

    void generateKLine(long time, int minute, int hour);
}
