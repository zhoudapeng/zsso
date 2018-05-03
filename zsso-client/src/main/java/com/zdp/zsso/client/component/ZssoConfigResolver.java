package com.zdp.zsso.client.component;

/**
 * 默认实现是读取本地classpath下zsso-client.properties配置文件，用户也可以自己实现此接口实现拓展
 */
public interface ZssoConfigResolver {
    /**
     * 本系统参数名，需要在server端备案
     * @return
     */
    String getSystemName();


    /**
     * 登录成功后生成本地cookie的domain
     * @return
     */
    String getSystemCookieDomain();


    /**
     * 服务端url前缀
     * @return
     */
    String getServerUrlPrefix();

}
