package com.zdp.zsso.client.component.impl;

import com.zdp.zsso.client.component.ZssoConfigResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ZssoConfigResolverImpl implements ZssoConfigResolver {
    private static final Logger logger = LoggerFactory.getLogger(ZssoConfigResolverImpl.class);
    private static String systemName;
    private static String systemCookieDomain;
    private static String serverUrlPrefix;

    static {
        try {
            Properties properties = new Properties();
            properties.load(ZssoConfigResolverImpl.class.getResourceAsStream("/zsso-client.properties"));
            systemName = properties.getProperty("zsso.system.name");
            systemCookieDomain = properties.getProperty("zsso.system.cookie.domain");
            serverUrlPrefix = properties.getProperty("zsso.server.url.prefix");
            logger.info("load zsso-client.properties,systemName=" + systemName + ",systemCookieDomain=" + systemCookieDomain + ",serverUrlPrefix=" + serverUrlPrefix);
        }catch (Exception e) {
            throw new RuntimeException("请正确配置zsso-client.properties配置文件",e);
        }
    }

    @Override
    public String getSystemName() {
        return systemName;
    }

    @Override
    public String getSystemCookieDomain() {
        return systemCookieDomain;
    }

    @Override
    public String getServerUrlPrefix() {
        return serverUrlPrefix;
    }

}
