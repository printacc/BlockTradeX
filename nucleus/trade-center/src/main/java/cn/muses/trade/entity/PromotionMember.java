package cn.muses.trade.entity;

import cn.muses.trade.constant.PromotionLevel;
import cn.muses.trade.constant.RealNameStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年03月20日
 */
@Data
@Builder
public class PromotionMember {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String username;
    private PromotionLevel level;
    private RealNameStatus realNameStatus = RealNameStatus.NOT_CERTIFIED;
}
