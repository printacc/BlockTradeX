package cn.muses.trade;

import cn.muses.trade.entity.CoinThumb;
import cn.muses.trade.entity.KLine;
import cn.muses.trade.service.MarketService;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.bson.Document;
import org.checkerframework.checker.units.qual.K;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class TradeMarketApplicationTests {


    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    MarketService marketService;

    @Test
    void contextLoads() {
        KLine kLine = new KLine();
//        Map<String, String> kMap = new HashMap<String,String>();
//        kMap.put("nadd","abcd");
//        kMap.put("nsub","杨金龙");
//        for (int i = 0; i < 1000; i++) {
//            kMap.put("工作"+i,"不找视频剪辑和电商运营和美工找工作只找开发java开发node开发智能合约开发"+i+2);
//
//        }
//
//
//        mongoTemplate.insert(Document.parse(new Gson().toJson(kMap)),"exchange_kline");
//        List<Document> exchange_kline = mongoTemplate.find(new Query(), Document.class, "exchange_kline");
//        for (Document document : exchange_kline) {
//            System.out.println(document.toJson());
//        }


        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long endTick = calendar.getTimeInMillis();
        String endTime = df.format(calendar.getTime());
        //往前推range个时间单位
        String fromTime = df.format(calendar.getTime());
        System.out.println("time range from " + fromTime + " to " + endTime);

        CoinThumb coinThumb = new CoinThumb();
        String symbol = "NaddTest";
        for (int i = 0; i <1000; i++) {
            kLine.setTime(endTick);
            kLine.setOpenPrice(coinThumb.getClose());
            kLine.setClosePrice(coinThumb.getClose());
            kLine.setLowestPrice(coinThumb.getClose());
            kLine.setHighestPrice(coinThumb.getClose());


            marketService.saveKLine(symbol, kLine);
        }



    }
}
