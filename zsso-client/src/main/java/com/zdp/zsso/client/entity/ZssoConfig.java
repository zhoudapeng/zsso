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
    private String ssoLoginUrl;

    /**
     * sso服务端校验token接口地址
     */
    private String ssoCheckTokenUrl;
    /**
     * 注销登录url，本地实现
     */
    private String logoutUrl;
    /**
     * 登录成功后sso服务端重定向的地址
     */
    private String loginSucUrl;

    public String getSsoLoginUrl() {
        return ssoLoginUrl;
    }

    public void setSsoLoginUrl(String ssoLoginUrl) {
        this.ssoLoginUrl = ssoLoginUrl;
    }

    public String getSsoCheckTokenUrl() {
        return ssoCheckTokenUrl;
    }

    public void setSsoCheckTokenUrl(String ssoCheckTokenUrl) {
        this.ssoCheckTokenUrl = ssoCheckTokenUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getLoginSucUrl() {
        return loginSucUrl;
    }

    public void setLoginSucUrl(String loginSucUrl) {
        this.loginSucUrl = loginSucUrl;
    }
}
