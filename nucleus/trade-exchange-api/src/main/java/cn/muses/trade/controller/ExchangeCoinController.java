package cn.muses.trade.controller;

//import cn.muses.trade.controller.BaseController;
import cn.muses.trade.service.ExchangeCoinService;
import cn.muses.trade.util.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @Title: ${file_name}
 * @Description:
 * @date 2019/4/1816:54
            */
    @RestController
    @RequestMapping("exchange-coin")
    public class ExchangeCoinController {
//    public class ExchangeCoinController extends BaseController {
        @Autowired
        private ExchangeCoinService service;

        //获取基币
        @RequestMapping("base-symbol")
    public MessageResult baseSymbol() {
        List<String> baseSymbol = service.getBaseSymbol();
        if (baseSymbol != null && baseSymbol.size() > 0) {
            return MessageResult.success(baseSymbol.toString());
        }
        return MessageResult.error("baseSymbol null");

    }

}
