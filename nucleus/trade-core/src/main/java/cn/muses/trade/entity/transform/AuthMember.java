package cn.muses.trade.entity.transform;

import cn.muses.trade.constant.CommonStatus;
import cn.muses.trade.constant.MemberLevelEnum;
import cn.muses.trade.entity.Location;
import cn.muses.trade.entity.Member;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Builder
@Data
public class AuthMember implements Serializable {
    private static final long serialVersionUID = -4199550203850153635L;
    private long id;
    private String name;
    private String realName;
    private Location location;
    private String mobilePhone;
    private String email;
    private MemberLevelEnum memberLevel;
    private CommonStatus status;

    /**
     * 如需添加信息在{@link #toAuthMember(Member)}方法中添加
     *
     * @param member
     * @return
     */
    public static AuthMember toAuthMember(Member member) {
        return AuthMember.builder()
                .id(member.getId())
                .name(member.getUsername())
                .realName(member.getRealName())
                .location(member.getLocation())
                .mobilePhone(member.getMobilePhone())
                .email(member.getEmail())
                .memberLevel(member.getMemberLevel())
                .status(member.getStatus())
                .build();
    }

}
