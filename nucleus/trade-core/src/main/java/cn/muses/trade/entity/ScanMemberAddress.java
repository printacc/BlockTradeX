package cn.muses.trade.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年01月26日
 */
@Builder
@Data
public class ScanMemberAddress {
    private long id;
    private String unit;
    private String remark;
    private String address;

    public static ScanMemberAddress toScanMemberAddress(MemberAddress memberAddress) {
        return ScanMemberAddress.builder().id(memberAddress.getId())
                .address(memberAddress.getAddress())
                .remark(memberAddress.getRemark())
                .unit(memberAddress.getCoin().getUnit())
                .build();
    }
}
