package com.zdp.zsso.server.dao.impl;

import com.zdp.zsso.server.dao.UserDAO;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/27
 * Time 上午10:13
 */
@Repository
public class UserDAOImpl implements UserDAO{
    @Override
    public String getUserId(String userName, String password) {
        if ("zhoudapeng".equals(userName) && "12345".equals(password)) {
            return "123456789";
        }
        return null;
    }
}
