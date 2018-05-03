package com.zdp.zsso.common.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/26
 * Time 下午6:47
 */
public class CookieBuilder {
    private String name;
    private String value;
    private String path;
    private String domain;
    private int expireSeconds;

    public CookieBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CookieBuilder setValue(String value) {
        this.value = value;
        return this;
    }

    public CookieBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public CookieBuilder setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public CookieBuilder setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
        return this;
    }

    public Cookie build() {
        if (name == null || value == null) {
            throw new IllegalArgumentException("cookie的name和value不能为空");
        }
        Cookie cookie = new Cookie(name,value);
        if (path != null) {
            cookie.setPath(path);
        }
        if (domain != null) {
            cookie.setDomain(domain);
        }
        cookie.setMaxAge(expireSeconds);
        return cookie;
    }
}
