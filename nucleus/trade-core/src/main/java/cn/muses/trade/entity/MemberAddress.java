package cn.muses.trade.entity;

import cn.muses.trade.constant.CommonStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 会员提币地址
 *
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年01月26日
 */
@Entity
@Data
public class MemberAddress {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deleteTime;
    @JoinColumn(name = "coin_id")
    @ManyToOne
    private Coin coin;
    private String address;
    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status=CommonStatus.NORMAL;
    private Long memberId;
    private String remark;

}
