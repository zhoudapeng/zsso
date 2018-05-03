package com.zdp.zsso.server.service.impl;

import com.zdp.zsso.server.service.UserService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/27
 * Time 上午10:28
 */
@Service
public class UserServiceImpl implements UserService{
    private ConcurrentHashMap<String,String> cache = new ConcurrentHashMap<>();

    @Override
    public boolean bound(String token, String userId) {
       String previousValue = cache.putIfAbsent(token,userId);
       return previousValue == null;
    }

    @Override
    public boolean unbound(String token) {
        String previousValue = cache.remove(token);
        return previousValue != null;
    }

    @Override
    public String query(String token) {
        return cache.get(token);
    }
}
