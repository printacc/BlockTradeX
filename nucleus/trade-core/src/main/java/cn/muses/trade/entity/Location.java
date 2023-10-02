package cn.muses.trade.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * 地址
 *
 * @author Hevin  E-mail:bizzanhevin@gmail.com
 * @date 2020年01月02日
 */
@Data
@Embeddable
public class Location implements Serializable {
    private String country;
    private String province;
    private String city;
    private String district;
}
