package cn.muses.trade.model.vo;

import cn.muses.trade.annotation.Excel;
import lombok.Data;

@Data
public class MemberTransactionFeeExcelVO {
    @Excel(name = "会员ID")
    private Long memberId;
    @Excel(name = "交易类型")
    private String type;
    @Excel(name = "手续费类型")
    private String symbol;
    @Excel(name = "交易时间")
    private String createTime;
    @Excel(name = "交易手续费")
    private String fee;

}
