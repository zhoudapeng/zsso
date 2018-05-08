package com.zdp.zsso.server.service.impl;

import com.zdp.zsso.server.dao.UserDAO;
import com.zdp.zsso.server.service.LoginService;
import com.zdp.zsso.server.service.TokenService;
import com.zdp.zsso.server.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/27
 * Time 上午10:16
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService{
    @Resource
    private UserDAO userDAO;
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;
    @Override
    public String login(String userName, String password) {
        String userId = userDAO.getUserId(userName,password);
        if (userId == null) {
            return null;
        }
        String token = tokenService.create().getToken();
        userService.bound(token,userId);
        return token;
    }
}
