package com.zdp.zsso.client.component.impl;

import com.zdp.zsso.client.component.UserStore;
import com.zdp.zsso.client.dao.UserDAO;
import com.zdp.zsso.client.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/25
 * Time 下午7:30
 */
@Component
public class UserStoreImpl implements UserStore {
    private Map<String,String> cache = new HashMap<>();
    @Resource
    private UserDAO userDAO;

    @Override
    public User resolve(String token) {
        String userId = cache.get(token);
        if (userId == null) {
            return null;
        }
        return userDAO.detail(userId);
    }

    @Override
    public void store(String token, String userId) {
        cache.put(token,userId);
    }

    @Override
    public void unbound(String token) {
        cache.remove(token);
    }
}
