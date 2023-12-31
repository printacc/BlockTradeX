package cn.muses.trade.vo;

import cn.muses.trade.annotation.Excel;
import cn.muses.trade.annotation.ExcelSheet;
import cn.muses.trade.constant.AdvertiseType;
import cn.muses.trade.constant.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ExcelSheet
public class OtcOrderVO {

    private Long id ;

    @Excel(name="订单编号")
    private String orderSn ;

    @Excel(name="交易时间")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime ;

    @Excel(name="广告发布者")
    private String memberName ;

    @Excel(name="交易者")
    private String customerName ;

    @Excel(name="币种单位")
    private String unit ;

    @Excel(name="广告类型")
    @Enumerated(value = EnumType.ORDINAL)
    private AdvertiseType advertiseType ;

    @Excel(name="交易金额")
    private BigDecimal money ;

    @Excel(name="交易数量")
    private BigDecimal number ;

    @Excel(name="手续费")
    private  BigDecimal fee ;

    @Excel(name="付款方式")
    private  String payMode ;

    @Excel(name="订单状态")
    @Enumerated(value = EnumType.ORDINAL)
    private OrderStatus status ;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cancelTime ;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime ;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime ;
}
