package com.zdp.zsso.client.filter;

import com.zdp.zsso.client.component.UrlHelper;
import com.zdp.zsso.client.component.UserStore;
import com.zdp.zsso.client.component.ZssoClient;
import com.zdp.zsso.client.component.ZssoConfigResolver;
import com.zdp.zsso.client.component.impl.ApplicationContextUtil;
import com.zdp.zsso.client.entity.CheckResultData;
import com.zdp.zsso.common.consts.ZssoConst;
import com.zdp.zsso.common.util.CookieBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/25
 * Time 下午7:14
 */
public class LoginCallbackFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LoginCallbackFilter.class);
    private UrlHelper urlHelper = ApplicationContextUtil.getBean(UrlHelper.class);
    private UserStore userStore = ApplicationContextUtil.getBean(UserStore.class);
    private ZssoConfigResolver zssoConfigResolver = ApplicationContextUtil.getBean(ZssoConfigResolver.class);
    private ZssoClient zssoClient = ApplicationContextUtil.getBean(ZssoClient.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init LoginCallbackFilter");
    }

    /**
     * 1.校验token
     * 2.根据校验token的结果查询用户信息
     * 3.绑定token和用户信息
     * 4.写本地域的cookie
     * 4.重定向到原始url
     * @param servletRequest
     * @param servletResponse
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getParameter(ZssoConst.TOKEN);
        CheckResultData checkResultData = zssoClient.check(token);
        String redirectUrl = request.getParameter(ZssoConst.REDIRECT_URL);
        if (checkResultData == null) {
            logger.info("token校验失败，重定向到sso登录页,token=" + token);
            response.sendRedirect(urlHelper.getServerLoginUrl(redirectUrl));
        }
        String userId = checkResultData.getUid();
        userStore.store(token,userId);
        Cookie cookie = new CookieBuilder().setName(ZssoConst.TOKEN).setValue(token).setDomain(zssoConfigResolver.getSystemCookieDomain()).setPath("/").setExpireSeconds((int)(checkResultData.getExpireTime() - System.currentTimeMillis())/1000).build();
        response.addCookie(cookie);
        response.sendRedirect(redirectUrl);
    }

    @Override
    public void destroy() {
        logger.info("destroy LoginCallbackFilter");
    }
}
