package cn.muses.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class WithdrawVO {

    private Long id ;
    private Long memberId ;
    private String memberUsername ;
    private String memberRealName ;
    private String phone ;
    private String email ;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dealTime ;
    private String unit ;
    private Double totalAmount ;
    private Double fee ;
    private Double arrivedAmount ;
    private String transactionNumber ;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime ;
    private Long addtime;
    private Long processtime;
    private String address ;
    private Integer status ;
    private String remark ;

}
