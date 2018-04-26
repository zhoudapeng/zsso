package com.zdp.zsso.client.filter;

import com.zdp.zsso.client.entity.CheckResultData;
import com.zdp.zsso.client.util.UserStore;
import com.zdp.zsso.client.util.ZssoConfigUtil;
import com.zdp.zsso.client.util.ZssoUtil;
import com.zdp.zsso.common.client.consts.ZssoConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/25
 * Time 下午7:14
 */
public class LoginSucFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LoginSucFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init LoginSucFilter");
    }

    /**
     * 1.校验token
     * 2.根据校验token的结果查询用户信息
     * 3.绑定token和用户信息
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
        CheckResultData checkResultData = ZssoUtil.check(token);
        if (checkResultData == null) {
            logger.info("token校验失败，重定向到sso登录页,token=" + token);
            response.sendRedirect(ZssoConfigUtil.getConfig().getSsoLoginUrl());
        }
        String userId = checkResultData.getUid();
        UserStore.store(token,userId);
        String redirectUrl = request.getParameter(ZssoConst.REDIRECT_URL);
        response.sendRedirect(redirectUrl);
    }

    @Override
    public void destroy() {
        logger.info("destroy LoginSucFilter");
    }
}
