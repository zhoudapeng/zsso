package com.zdp.zsso.client.component;

public interface UserStore<T> {
    /**
     * 通过token解析用户信息
     * @param token
     * @return
     */
    T resolve(String token);

    /**
     * 绑定token到本地系统
     * @param token
     * @param userId
     */
    void bound(String token, String userId);

    /**
     * 本地用户信息解绑
     * @param token
     */
    void unbound(String token);
}
