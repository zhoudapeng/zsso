package com.zdp.zsso.client.component;

/**
 * 拼接url的类，默认实现类是根据本地classpath下zsso-client.properties配置文件生成，用户可自己实现此接口实现二次开发
 */
public interface UrlHelper {
    /**
     * 当检测到未登录时生成重定向url
     * @param redirectUrl
     * @return
     */
    String getServerLoginUrl(String redirectUrl);

    /**
     * zsso服务端重定向到本地系统后检测token的url
     * @param token
     * @return
     */
    String getCheckUrl(String token);

    /**
     * 注销url
     * @return
     */
    String getLogoutUrl();
}
