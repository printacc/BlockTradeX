
package cn.muses.trade.core.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HawkMethod {
	/**
     * 指令号
     * @return
     */
    int cmd();

    /**
     * 指令版本号
     * @return
     */
    byte version() default 1;

    /**
     * 服务方法是否已经过期，默认不过期
     */
    ObsoletedType obsoleted() default ObsoletedType.NO;
}
