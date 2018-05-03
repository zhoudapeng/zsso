package com.zdp.zsso.client.component.impl;

import com.zdp.zsso.client.component.UserStore;
import com.zdp.zsso.client.entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/25
 * Time 下午7:30
 */
public class UserStoreImpl implements UserStore<User> {
    private Map<String, String> cache = new ConcurrentHashMap<>();

    @Override
    public User resolve(String token) {
        String userId = cache.get(token);
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setUserId(userId);
        user.setUserName("This is just for demo,please implements UserStore yourself");
        return user;
    }

    @Override
    public void bound(String token, String userId) {
        cache.put(token, userId);
    }

    @Override
    public void unbound(String token) {
        cache.remove(token);
    }

}
