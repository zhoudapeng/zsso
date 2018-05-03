package com.zdp.zsso.client.component;


import com.zdp.zsso.common.entity.CheckResultData;

/**
 * 封装对ZssoServer端的调用，默认实现是使用HttpClient调用，用户可自己实现此接口实现定制
 */
public interface ZssoClient {
    /**
     * 检测token合法性
     * @param token
     * @return
     */
    CheckResultData check(String token);
}
