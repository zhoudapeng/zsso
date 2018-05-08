package com.zdp.zsso.server.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/5/8
 * Time 下午1:37
 */
public class TokenDetail {
    private String token;
    private Long expireTimeMillis ;
    private List<String> systemNames = new ArrayList<>();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpireTimeMillis() {
        return expireTimeMillis;
    }

    public void setExpireTimeMillis(Long expireTimeMillis) {
        this.expireTimeMillis = expireTimeMillis;
    }

    public List<String> getSystemNames() {
        return systemNames;
    }

    public void setSystemNames(List<String> systemNames) {
        this.systemNames = systemNames;
    }

    public void bound(String systemName) {
        systemNames.add(systemName);
    }
}
