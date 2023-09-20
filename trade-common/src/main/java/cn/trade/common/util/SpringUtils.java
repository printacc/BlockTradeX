package cn.trade.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;


@Component
public final class SpringUtils implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    public static ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    public static <T> T getBean(Class<T> name) throws BeansException {
        if (getBeanFactory() == null) {
            System.out.println("本地调试Main模式，没有BeanFactory，忽略错误");
            return null;
        } else {
            T result = (T) beanFactory.getBean(name);
            return result;
        }
    }


}
