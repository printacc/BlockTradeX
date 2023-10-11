package cn.muses.trade.entity;

import lombok.Data;

import java.util.List;

@Data
public class ExchangeOrderMessage {
    private Long time;
    private String symbol;
    private List<ExchangeOrder> orders;
}
