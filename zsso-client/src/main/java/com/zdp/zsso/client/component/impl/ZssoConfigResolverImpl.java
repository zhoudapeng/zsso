package com.zdp.zsso.client.component.impl;

import com.zdp.zsso.client.component.ZssoConfigResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ZssoConfigResolverImpl implements ZssoConfigResolver {
    @Value("zsso.system.name")
    private String systemName;
    @Value("zsso.system.cookie.domain")
    private String systemCookieDomain;
    @Value("zsso.server.url.prefix")
    private String serverUrlPrefix;

    @Override
    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @Override
    public String getSystemCookieDomain() {
        return systemCookieDomain;
    }

    public void setSystemCookieDomain(String systemCookieDomain) {
        this.systemCookieDomain = systemCookieDomain;
    }

    @Override
    public String getServerUrlPrefix() {
        return serverUrlPrefix;
    }

    public void setServerUrlPrefix(String serverUrlPrefix) {
        this.serverUrlPrefix = serverUrlPrefix;
    }
}
