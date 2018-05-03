package com.zdp.zsso.server.controller;

import com.zdp.zsso.common.consts.ZssoConst;
import com.zdp.zsso.common.util.CookieBuilder;
import com.zdp.zsso.server.component.ConfigLoader;
import com.zdp.zsso.server.service.LoginService;
import com.zdp.zsso.server.service.TokenService;
import com.zdp.zsso.server.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/26
 * Time 下午7:20
 */
@Controller
@RequestMapping("/")
public class LoginController {
    @Resource
    private LoginService loginService;
    @Resource
    private ConfigLoader configLoader;
    @Resource
    private TokenService tokenService;
    @Resource
    private UserService userService;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(@RequestParam("systemName") String systemName,
                              @RequestParam("redirectUrl") String redirectUrl,
                              @RequestParam(value = "errorMsg",required = false,defaultValue = "")String errorMsg,
                              HttpServletRequest request,RedirectAttributes attrs) {
        ModelAndView mav = new ModelAndView("login/login");
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            mav.addObject("systemName",systemName);
            mav.addObject("redirectUrl",redirectUrl);
            mav.addObject("errorMsg",errorMsg);
            return mav;
        }
        for (Cookie cookie:cookies) {
            if (ZssoConst.COOKIE_NAME_TOKEN.equals(cookie.getName())) {
                String token = cookie.getValue();
                String userId = userService.query(token);
                if (userId != null) {
                    tokenService.bound(token,systemName);
                    attrs.addAttribute(ZssoConst.PARAM_NAME_TOKEN,token);
                    attrs.addAttribute(ZssoConst.PARAM_NAME_REDIRECT_URL,redirectUrl);
                    mav.setViewName("redirect:" + configLoader.getUrl(systemName) + "/zsso/callback");
                    return mav;
                }
            }
        }

        return mav;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("userName")String userName,
                              @RequestParam("password")String password,
                              @RequestParam("systemName")String systemName,
                              @RequestParam("redirectUrl")String redirectUrl,
                              RedirectAttributes attrs,
                              HttpServletResponse response) throws Exception {
        String token = loginService.login(userName,password);
        if (token == null) {
            attrs.addAttribute("systemName",systemName);
            attrs.addAttribute("redirectUrl",redirectUrl);
            attrs.addAttribute("errorMsg","账号或密码错误");
            return "redirect:/login";
        }
        tokenService.bound(token,systemName);
        Cookie cookie = new CookieBuilder().setName(ZssoConst.COOKIE_NAME_TOKEN).setValue(token).setPath("/").setDomain(configLoader.getServerDomain()).setExpireSeconds((int) TimeUnit.DAYS.toSeconds(1)).build();
        response.addCookie(cookie);
        String url = configLoader.getUrl(systemName);
        attrs.addAttribute(ZssoConst.PARAM_NAME_TOKEN,token);
        attrs.addAttribute(ZssoConst.PARAM_NAME_REDIRECT_URL,redirectUrl);
        return "redirect:" + url + "/zsso/callback";
    }
}
