package com.zdp.zsso.client.filter;

import com.zdp.zsso.client.component.UserStore;
import com.zdp.zsso.client.component.ZssoConfigResolver;
import com.zdp.zsso.client.component.impl.ApplicationContextUtil;
import com.zdp.zsso.common.consts.ZssoConst;
import com.zdp.zsso.common.util.CookieBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/25
 * Time 下午5:05
 */
class LogoutFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LogoutFilter.class);
    private UserStore userStore = ApplicationContextUtil.getBean(UserStore.class);
    private ZssoConfigResolver zssoConfigResolver = ApplicationContextUtil.getBean(ZssoConfigResolver.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init LogoutFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = servletRequest.getParameter(ZssoConst.PARAM_NAME_TOKEN);
        logger.info("登出请求,token=" + token);
        userStore.unbound(token);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.getWriter().write("{\"bstatus\":{\"code\":0,\"des\":\"成功\"}}");
    }

    @Override
    public void destroy() {
        logger.info("destroy LogoutFilter");
    }

}
