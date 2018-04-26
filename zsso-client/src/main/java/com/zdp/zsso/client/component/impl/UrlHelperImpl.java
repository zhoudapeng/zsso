package com.zdp.zsso.client.component.impl;

import com.zdp.zsso.client.component.UrlHelper;
import com.zdp.zsso.client.component.ZssoConfigResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URLEncoder;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/26
 * Time 下午6:35
 */
@Component
public class UrlHelperImpl implements UrlHelper {
    private static final Logger logger = LoggerFactory.getLogger(UrlHelperImpl.class);
    private static final String LOGIN_URL_FORMAT = "%s/login?serviceName=%s&redirectUrl=%s";
    private static final String CHECK_URL_FORMAT = "%s/check?serviceName=%s&token=%s";

    @Resource
    private ZssoConfigResolver zssoConfigResolver;

    @Override
    public String getServerLoginUrl(String redirectUrl) {
        String serverUrlPrefix = zssoConfigResolver.getServerUrlPrefix();
        String systemName = zssoConfigResolver.getSystemName();
        try {
            redirectUrl = URLEncoder.encode(redirectUrl,"utf-8");
        }catch (Exception e) {
            logger.info("url encode error,url=" + redirectUrl);
            redirectUrl = "";
        }
        return String.format(LOGIN_URL_FORMAT,serverUrlPrefix,systemName,redirectUrl);
    }

    @Override
    public String getCheckUrl(String token) {
        return String.format(CHECK_URL_FORMAT,zssoConfigResolver.getServerUrlPrefix(),zssoConfigResolver.getSystemName());
    }
}
