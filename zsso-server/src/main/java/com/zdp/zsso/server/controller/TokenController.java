package com.zdp.zsso.server.controller;

import com.zdp.zsso.common.entity.Bstatus;
import com.zdp.zsso.common.entity.CheckResult;
import com.zdp.zsso.common.entity.CheckResultData;
import com.zdp.zsso.common.util.JsonUtil;
import com.zdp.zsso.server.service.TokenService;
import com.zdp.zsso.server.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/27
 * Time 下午1:12
 */
@Controller
@RequestMapping("/token")
public class TokenController {
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;
    @RequestMapping("/check")
    @ResponseBody
    public String check(@RequestParam("systemName")String systemName,@RequestParam("token") String token) {
        String userId = userService.query(token);
        if (userId == null) {
            CheckResult result = new CheckResult();
            result.setBstatus(new Bstatus(-1,"无效token"));
            return JsonUtil.toJson(result);
        }
        CheckResultData checkResultData = new CheckResultData();
        checkResultData.setToken(token);
        checkResultData.setUid(userId);
        checkResultData.setExpireTime(tokenService.detail(token).getExpireTimeMillis());
        tokenService.bound(token, systemName);
        CheckResult result = new CheckResult();
        result.setBstatus(new Bstatus());
        result.setData(checkResultData);
        return JsonUtil.toJson(result);
    }
}
