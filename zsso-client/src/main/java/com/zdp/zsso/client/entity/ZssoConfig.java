package com.zdp.zsso.client.entity;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/25
 * Time 下午5:06
 */
public class ZssoConfig {
    /**
     * sso服务端登录页地址
     */
    private String systemName;

    /**
     * 本系统cookie域
     */
    private String systemCookieDomain;
    /**
     * zsso服务端域名
     */
    private String serverUrlPrefix;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemCookieDomain() {
        return systemCookieDomain;
    }

    public void setSystemCookieDomain(String systemCookieDomain) {
        this.systemCookieDomain = systemCookieDomain;
    }

    public String getServerUrlPrefix() {
        return serverUrlPrefix;
    }

    public void setServerUrlPrefix(String serverUrlPrefix) {
        this.serverUrlPrefix = serverUrlPrefix;
    }
}
