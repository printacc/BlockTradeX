package cn.muses.trade.vo;

import cn.muses.trade.constant.PromotionLevel;
import lombok.Data;

import java.util.Date;

@Data
public class MemberPromotionStasticVO {
    private Long id;

    //邀请者Id
    private Long inviterId;
    
    //受邀者Id
    private Long inviteesId;

    private PromotionLevel level;
    
    private Date createTime;
    
    private int count;
}
