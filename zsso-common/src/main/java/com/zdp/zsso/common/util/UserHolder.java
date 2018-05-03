package com.zdp.zsso.common.util;

import com.zdp.zsso.common.consts.ZssoConst;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/5/2
 * Time 下午1:35
 */
public class UserHolder {
    public static <T> T get(HttpServletRequest request,Class<T> clazz) {
        Object obj = request.getAttribute(ZssoConst.ATTIBUTE_NAME_LOGIN_USER);
        return clazz.cast(obj);
    }
}
