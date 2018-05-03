package com.zdp.zsso.client.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/27
 * Time 下午6:13
 */
public class ZssoFilter implements Filter{
    private static final Logger logger = LoggerFactory.getLogger(ZssoFilter.class);
    private Map<String,Filter> FILTER_MAP = new HashMap<>();
    private Filter loginCheckFilter = new LoginCheckFilter();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init ZssoFilter");
        FILTER_MAP.put("/zsso/callback",new LoginCallbackFilter());
        FILTER_MAP.put("/zsso/logout",new LogoutFilter());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        Filter filter = FILTER_MAP.get(uri);
        if (filter != null) {
            logger.info("uri=" + uri + ",将处理相关逻辑");
            filter.doFilter(request,servletResponse,chain);
            return;
        }
        loginCheckFilter.doFilter(servletRequest,servletResponse,chain);
    }

    @Override
    public void destroy() {
        logger.info("destroy ZssoFilter");
    }

    public static void main(String[] args) {
        System.out.println("IPAYNOW:短信请忽略,您的退款订单-2#,（WIFA无限流量租赁商家同意退款，04281600,金额00元人民币. 款项1日后到账.".length());
    }

}
