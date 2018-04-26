package com.zdp.zsso.client.filter;

import com.zdp.zsso.client.entity.User;
import com.zdp.zsso.client.util.UserStore;
import com.zdp.zsso.client.util.ZssoConfigUtil;
import com.zdp.zsso.common.client.consts.ZssoConst;
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
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

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
                User user = UserStore.resolve(token);
                if (user != null) {
                    request.setAttribute("LOGIN_USER",user);
                    chain.doFilter(request,response);
                }
            }
        }
        sendToLogin(request,response);
    }

    private void sendToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURL = request.getRequestURL().toString();
        String loginUrl = ZssoConfigUtil.getConfig().getSsoLoginUrl();
        String redirectUrl = loginUrl;
        try {
            redirectUrl = loginUrl + "?redirectUrl=" + URLEncoder.encode(requestURL,"utf-8");
        } catch (Exception e) {
            logger.warn("encode requestUrl失败,直接重定向到登录页，requestUrl=" + redirectUrl);
        }
        response.sendRedirect(redirectUrl);

    }

    @Override
    public void destroy() {

    }
}
