package cn.muses.trade.entity;

import cn.muses.trade.constant.BooleanEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年01月16日
 */
@Builder
@Data
public class MemberAccount {
    private String realName;
    private BooleanEnum bankVerified;
    private BooleanEnum aliVerified;
    private BooleanEnum wechatVerified;
    private BankInfo bankInfo;
    private Alipay alipay;
    private WechatPay wechatPay;
}
