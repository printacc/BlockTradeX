package cn.muses.trade.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @description 会员等级实体
 */
@Data
@Entity
@Table(name = "member_level")
public class MemberLevel {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @NotBlank(message = "会员等级名称不得为空")
    private String name;
    @NotNull(message = "默认不得为null")
    private Boolean isDefault;
    private String remark;

}
