package com.zdp.zsso.server.component;

public interface ConfigLoader {
    String getServerDomain();
    String getUrl(String systemName);
}
