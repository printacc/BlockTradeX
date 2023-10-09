package cn.muses.trade.handler;

import cn.muses.trade.entity.CoinThumb;
import cn.muses.trade.entity.ExchangeTrade;
import cn.muses.trade.entity.KLine;

public interface MarketHandler {

    /**
     * 存储交易信息
     * @param exchangeTrade
     */
    void handleTrade(String symbol, ExchangeTrade exchangeTrade, CoinThumb thumb);


    /**
     * 存储K线信息
     *
     * @param kLine
     */
    void handleKLine(String symbol,KLine kLine);
}
