package com.zdp.zsso.client.filter;

import com.zdp.zsso.client.component.UrlHelper;
import com.zdp.zsso.client.component.UserStore;
import com.zdp.zsso.client.component.ZssoConfigResolver;
import com.zdp.zsso.client.component.impl.ApplicationContextUtil;
import com.zdp.zsso.client.component.impl.ZssoConfigResolverImpl;
import com.zdp.zsso.client.entity.User;
import com.zdp.zsso.client.entity.ZssoConfig;
import com.zdp.zsso.client.component.impl.UserStoreImpl;
import com.zdp.zsso.common.consts.ZssoConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/25
 * Time 下午6:43
 */
public class LoginCheckFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LoginCheckFilter.class);
    private UserStore userStore = ApplicationContextUtil.getBean(UserStore.class);
    private UrlHelper urlHelper = ApplicationContextUtil.getBean(UrlHelper.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init LoginCheckFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            sendToLogin(request,response);
        }
        for (Cookie cookie:cookies) {
            if (ZssoConst.TOKEN.equals(cookie.getName())) {
                String token = cookie.getName();
                User user = userStore.resolve(token);
                if (user != null) {
                    request.setAttribute("LOGIN_USER",user);
                    chain.doFilter(request,response);
                }
            }
        }
        sendToLogin(request,response);
    }

    private void sendToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = urlHelper.getServerLoginUrl(request.getRequestURL().toString());
        response.sendRedirect(url);
    }

    @Override
    public void destroy() {
        logger.info("destroy LoginCheckFilter");
    }
}
