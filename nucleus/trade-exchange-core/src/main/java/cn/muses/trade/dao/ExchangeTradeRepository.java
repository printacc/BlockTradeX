package cn.muses.trade.dao;

import cn.muses.trade.entity.ExchangeTrade;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExchangeTradeRepository extends MongoRepository<ExchangeTrade,String> {
}
