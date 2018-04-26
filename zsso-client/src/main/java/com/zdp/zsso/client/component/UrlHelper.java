package com.zdp.zsso.client.component;

public interface UrlHelper {
    String getServerLoginUrl(String redirectUrl);

    String getCheckUrl(String token);
}
