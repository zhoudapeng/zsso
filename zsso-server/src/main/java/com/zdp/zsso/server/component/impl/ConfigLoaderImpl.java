package com.zdp.zsso.server.component.impl;

import com.zdp.zsso.server.component.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/27
 * Time 下午1:02
 */
@Component
public class ConfigLoaderImpl implements ConfigLoader,InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(ConfigLoaderImpl.class);
    @Value("${server.domain}")
    private String serverDomain;
    @Value("${wiki.url}")
    private String wikiUrl;
    @Value("${bbs.url}")
    private String bbsUrl;

    private Map<String,String> cache = new HashMap<>();

    @Override
    public String getUrl(String systemName) {
        return cache.get(systemName);
    }

    @Override
    public String getServerDomain() {
        return serverDomain;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        cache.put("wiki",wikiUrl);
        cache.put("bbs",bbsUrl);
    }
}
