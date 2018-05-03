package com.zdp.zsso.client.component.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/26
 * Time 下午5:54
 */
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static  <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
