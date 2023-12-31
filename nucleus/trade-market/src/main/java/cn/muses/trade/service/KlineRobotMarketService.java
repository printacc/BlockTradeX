package cn.muses.trade.service;

import cn.muses.trade.entity.KLine;
import cn.muses.trade.entity.Symbol;

import java.util.List;

public interface KlineRobotMarketService {


    public void saveKLine(String symbol, KLine kLine);

    /**
     * 获取K最近一条K线的时间
     * @param symbol
     * @param period
     * @return
     */
    public long findMaxTimestamp(String symbol, String period);

    public List<Symbol> findAllSymbol();

    public void addSymbol(Symbol symbol);

    public void deleteAll(String symbol);
}
