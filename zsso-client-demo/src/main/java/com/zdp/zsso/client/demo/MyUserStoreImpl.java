package com.zdp.zsso.client.demo;

import com.zdp.zsso.client.component.UserStore;
import com.zdp.zsso.client.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/5/3
 * Time 上午10:55
 */
@Service("userStore")
public class MyUserStoreImpl implements UserStore<User>,InitializingBean{
    private static final Logger logger = LoggerFactory.getLogger(MyUserStoreImpl.class);
    private Map<String,String> cache = new HashMap<>();
    @Override
    public User resolve(String token) {
        if (cache.containsKey(token)) {
            String userId = cache.get(token);
            User user = new User();
            user.setUserId("demo_id_" + userId);
            user.setUserName("demo_name_" + userId);
            return user;
        }
        return null;
    }

    @Override
    public void bound(String token, String userId) {
        cache.put(token,userId);
    }

    @Override
    public void unbound(String token) {
        cache.remove(token);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("初始化MyUserStoreImpl");
    }
}
