package com.zdp.zsso.client.util;

import com.zdp.zsso.client.component.PropertiesZssoConfigResolver;
import com.zdp.zsso.client.entity.ZssoConfig;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/25
 * Time 下午7:19
 */
public class ZssoConfigUtil {
    public static ZssoConfig getConfig() {
        return new PropertiesZssoConfigResolver().resolve();
    }
}
