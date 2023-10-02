package cn.muses.trade.constant;

import cn.muses.trade.core.BaseEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
public enum AuditStatus implements BaseEnum{

    AUDIT_ING("待审核"),
    AUDIT_DEFEATED("审核失败"),
    AUDIT_SUCCESS("审核成功");

    @Setter
    private String cnName;
    @Override
    @JsonValue
    public int getOrdinal() {
        return this.ordinal();
    }
}
