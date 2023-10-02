package cn.muses.trade.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContractDTO {
    private String name;
    private String address;
    private int decimals;
    private BigDecimal minCollectAmount;
}
