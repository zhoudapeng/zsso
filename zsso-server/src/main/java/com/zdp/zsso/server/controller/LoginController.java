package com.zdp.zsso.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/26
 * Time 下午7:20
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    public ModelAndView login(@RequestParam("systemName") String systemName, @RequestParam("redirectUrl") String redirectUrl) {
        ModelAndView mav = new ModelAndView("login/login");
        mav.addObject("systemName",systemName);
        mav.addObject("redirectUrl",redirectUrl);
        return mav;
    }
}
