package com.zdp.zsso.client.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/28
 * Time 上午11:43
 */
public class ZssoNamespaceHandler extends NamespaceHandlerSupport  {
    @Override
    public void init() {
        this.registerBeanDefinitionParser("auto-driven", new ZssoBeanDefinitionParser());
    }
}
