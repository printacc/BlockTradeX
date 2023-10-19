package cn.muses.trade.controller.authno;

import cn.muses.trade.controller.common.BaseAdminController;
import cn.muses.trade.entity.ExchangeCoin;
import cn.muses.trade.service.ExchangeCoinService;
import cn.muses.trade.util.MessageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static org.springframework.util.Assert.notNull;

import static cn.muses.trade.util.MessageResult.success;
import static cn.muses.trade.util.MessageResult.error;

@RestController
@RequestMapping("noauth/exchange-coin")
public class AuthExchangeCoinController extends BaseAdminController {
	private Logger logger = LoggerFactory.getLogger(AuthExchangeCoinController.class);
	
	@Autowired
    private ExchangeCoinService exchangeCoinService;
	
    @PostMapping("detail")
    public MessageResult detail(
            @RequestParam(value = "symbol") String symbol,
            @RequestParam(value = "sign") String sign) {
    	if(!sign.equals("77585211314qazwsx")) {
    		return error("非法访问");
    	}
        ExchangeCoin exchangeCoin = exchangeCoinService.findBySymbol(symbol);
        notNull(exchangeCoin, "validate symbol!");
        return success(exchangeCoin.toString());
    }
	
    @PostMapping("modify-limit")
    public MessageResult modifyLimit(
            @RequestParam(value = "symbol") String symbol,
            @RequestParam(value = "maxBuyPrice") BigDecimal maxBuyPrice,
            @RequestParam(value = "minSellPrice") BigDecimal minSellPrice,
            @RequestParam(value = "sign") String sign) {
    	
    	if(!sign.equals("77585211314qazwsx")) {
    		return error("非法访问");
    	}
    	
        ExchangeCoin exchangeCoin = exchangeCoinService.findBySymbol(symbol);
        notNull(exchangeCoin, "validate symbol!");
        
        exchangeCoin.setMaxBuyPrice(maxBuyPrice);
        exchangeCoin.setMinSellPrice(minSellPrice);
        exchangeCoinService.save(exchangeCoin);
        
        return success(exchangeCoin.toString());
    }
}
