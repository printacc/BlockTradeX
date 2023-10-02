package cn.muses.trade.constant;

import cn.muses.trade.core.BaseEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
public enum PayMode implements BaseEnum {
    ALI("支付宝"), WECHAT("微信"), BANK("银行卡");

    @Setter
    private String cnName;

    @Override
    @JsonValue
    public int getOrdinal() {
        return ordinal();
    }
}
