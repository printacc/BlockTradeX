package cn.muses.trade.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
public class AgentWalletDTO extends BaseMemberDTO{

    private Long id;

    private String coinUnit ;

    private BigDecimal balance;
}
