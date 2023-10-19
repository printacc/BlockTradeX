package cn.muses.trade.model.screen;

import lombok.Data;

@Data
public class ExchangeCoinScreen {
	
	//交易币种符号
    private String coinSymbol;
    
    //结算币种符号，如USDT
    private String baseSymbol;
}
