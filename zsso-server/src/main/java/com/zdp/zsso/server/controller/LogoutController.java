package com.zdp.zsso.server.controller;

import com.zdp.zsso.common.consts.ZssoConst;
import com.zdp.zsso.common.util.CookieBuilder;
import com.zdp.zsso.common.util.HttpClientUtil;
import com.zdp.zsso.server.component.ConfigLoader;
import com.zdp.zsso.server.service.TokenService;
import com.zdp.zsso.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/27
 * Time 下午7:20
 */
@Controller
@RequestMapping("/")
public class LogoutController {
    private static Logger logger = LoggerFactory.getLogger(LogoutController.class);
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;
    @Resource
    private ConfigLoader configLoader;

    @RequestMapping("/logout")
    public String logout(@RequestParam("systemName")String systemName, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            if (ZssoConst.COOKIE_NAME_TOKEN.equals(cookie.getName())) {
                String token = cookie.getValue();
                userService.unbound(token);
                List<String> systemNames = tokenService.detail(token).getSystemNames();
                tokenService.remove(token);
                if (!CollectionUtils.isEmpty(systemNames)) {
                    Map<String,String> params = new HashMap<>();
                    params.put(ZssoConst.PARAM_NAME_TOKEN,token);
                    for (String name:systemNames) {
                        String url = configLoader.getUrl(name);
                        String result = HttpClientUtil.doPost(url + "/zsso/logout",params);
                        logger.info("请求logout接口，url=" + url + ",params=" + params + ",返回值:" + result);
                    }
                }
            }
        }
        Cookie cookie = new CookieBuilder().setName(ZssoConst.COOKIE_NAME_TOKEN).setValue("").setPath("/").setDomain(configLoader.getServerDomain()).setExpireSeconds(0).build();
        response.addCookie(cookie);
        attributes.addAttribute("systemName",systemName);
        attributes.addAttribute("redirectUrl",request.getHeader("referer"));
        return "redirect:/login";
    }
}
