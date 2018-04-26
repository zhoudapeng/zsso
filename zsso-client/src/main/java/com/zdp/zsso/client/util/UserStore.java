package com.zdp.zsso.client.util;

import com.zdp.zsso.client.entity.User;
import com.zdp.zsso.common.client.util.JsonUtil;
import redis.clients.jedis.Jedis;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/25
 * Time 下午7:30
 */
public class UserStore {
    private static Jedis jedis = new Jedis("127.0.0.1");
    public static User resolve(String token) {
        String userInfo = jedis.get(token);
        if (userInfo == null) {
            return null;
        }
        return JsonUtil.fromJson(userInfo,User.class);
    }

    public static void store(String token,String userId) {
        // 这里可以去查询DB，获取用户详细信息
        User user = new User();
        user.setUserId(userId);
        jedis.set(token,JsonUtil.toJson(user));
    }
}
