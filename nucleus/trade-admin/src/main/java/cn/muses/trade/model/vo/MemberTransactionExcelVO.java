package cn.muses.trade.model.vo;

import cn.muses.trade.annotation.Excel;
import lombok.Data;

@Data
public class MemberTransactionExcelVO {
    @Excel(name = "会员ID")
    private Long memberId;
    @Excel(name = "交易类型")
    private String type;
    @Excel(name = "交易金额")
    private String amount;
    @Excel(name = "交易手续费")
    private String fee;
    @Excel(name = "交易时间")
    private String createTime;

}
