package com.zdp.zsso.client.config;

import com.zdp.zsso.client.component.impl.ComponentInitializer;
import com.zdp.zsso.client.component.impl.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.lang.Nullable;
import org.w3c.dom.Element;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/28
 * Time 上午11:45
 */
public class ZssoBeanDefinitionParser implements BeanDefinitionParser {

    @Nullable
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionRegistry registry = parserContext.getRegistry();
        RootBeanDefinition applicationContextUtil = new RootBeanDefinition(ApplicationContextUtil.class);
        registry.registerBeanDefinition("applicationContextUtil",applicationContextUtil);
        RootBeanDefinition defaultComponentInitializertDefinition = new RootBeanDefinition(ComponentInitializer.class);
        registry.registerBeanDefinition("componentInitializer",defaultComponentInitializertDefinition);
        return null;
    }
}
