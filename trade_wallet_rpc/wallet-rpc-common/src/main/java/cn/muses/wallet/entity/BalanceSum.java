package cn.muses.wallet.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceSum {
    private BigDecimal totalBalance;
}
