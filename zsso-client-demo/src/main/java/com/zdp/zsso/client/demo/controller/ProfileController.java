package com.zdp.zsso.client.demo.controller;

import com.zdp.zsso.client.component.UrlHelper;
import com.zdp.zsso.client.component.ZssoConfigResolver;
import com.zdp.zsso.client.entity.User;
import com.zdp.zsso.common.util.UserHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/5/2
 * Time 下午12:52
 */
@RequestMapping("/profile")
@Controller
public class ProfileController {
    @Resource
    private UrlHelper urlHelper;
    @RequestMapping("/detail")
    public ModelAndView detail(HttpServletRequest request) {
        User user = UserHolder.get(request, User.class);
        ModelAndView mav = new ModelAndView("profile/detail");
        mav.addObject("user",user);
        mav.addObject("logoutUrl",urlHelper.getLogoutUrl());
        return mav;
    }
}
