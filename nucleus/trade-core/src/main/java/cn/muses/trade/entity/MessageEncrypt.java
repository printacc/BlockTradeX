package cn.muses.trade.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MessageEncrypt {

    private String message;

    private String password;
}
