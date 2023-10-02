package cn.muses.trade.constant;

import cn.muses.trade.core.BaseEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年03月08日
 */
@AllArgsConstructor
@Getter
public enum RewardRecordType implements BaseEnum {
    /**
     * 推广
     */
    PROMOTION("推广"),
    /**
     * 活动
     */
    ACTIVITY("活动"),
    /**
     * 分红
     */
    DIVIDEND("分红");
    @Setter
    private String cnName;

    @Override
    @JsonValue
    public int getOrdinal() {
        return ordinal();
    }

}
