package com.zdp.zsso.client.component.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * 组件初始化类，当发现用户没有自己实现相关组件时采用默认实现
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/28
 * Time 上午11:18
 */
public class ComponentInitializer implements ApplicationContextAware{
    private static Logger logger = LoggerFactory.getLogger(ComponentInitializer.class);

    private static Map<String,Class> DEFAULT_COMPONENT_CLAZZ = new HashMap<>();

    static {
        DEFAULT_COMPONENT_CLAZZ.put("urlHelper",UrlHelperImpl.class);
        DEFAULT_COMPONENT_CLAZZ.put("userStore",UserStoreImpl.class);
        DEFAULT_COMPONENT_CLAZZ.put("zssoClient",ZssoClientImpl.class);
        DEFAULT_COMPONENT_CLAZZ.put("zssoConfigResolver",ZssoConfigResolverImpl.class);
    }

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
        for (Map.Entry<String,Class> entry:DEFAULT_COMPONENT_CLAZZ.entrySet()) {
            try {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(entry.getKey());
                logger.info("component class " + entry.getKey() + " in context:" + beanDefinition.getBeanClassName());
            } catch (NoSuchBeanDefinitionException e) {
                logger.info("component " + entry.getKey() + " use default impl " + entry.getValue());
                RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(entry.getValue());
                rootBeanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
                beanFactory.registerBeanDefinition(entry.getKey(),rootBeanDefinition);
            }
        }
    }
}
