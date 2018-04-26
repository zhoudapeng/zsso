package com.zdp.zsso.common.util;

import com.google.gson.Gson;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/25
 * Time 下午5:28
 */
public class JsonUtil {
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String)obj;
        }
        return new Gson().toJson(obj);
    }

    public static <T> T fromJson(String json,Class<T> clazz) {
        return new Gson().fromJson(json,clazz);
    }
}
