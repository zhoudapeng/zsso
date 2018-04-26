package com.zdp.zsso.client.dao.impl;

import com.zdp.zsso.client.dao.UserDAO;
import com.zdp.zsso.client.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/26
 * Time 下午6:13
 */
@Repository
public class UserDAODemo implements UserDAO{
    @Override
    public User detail(String userId) {
        User user = new User();
        user.setUserId(userId);
        user.setUserName("测试用户名");
        return user;
    }
}
