package cn.muses.trade.core.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 在服务类中标该类，以便确定服务方法所属的组及相关信息
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface HawkBean {
    /**
     * 指令版本号
     * @return
     */
    byte version() default 0;
}
