package com.zdp.zsso.client.util;

import com.zdp.zsso.client.component.PropertiesZssoConfigResolver;
import com.zdp.zsso.client.component.ZssoConfigResolver;
import com.zdp.zsso.client.entity.CheckResult;
import com.zdp.zsso.client.entity.CheckResultData;
import com.zdp.zsso.common.client.consts.ZssoConst;
import com.zdp.zsso.common.client.util.HttpClientUtil;
import com.zdp.zsso.common.client.util.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/25
 * Time 下午5:03
 */
public class ZssoUtil {
    private static ZssoConfigResolver configResolver = new PropertiesZssoConfigResolver();
    public static CheckResultData check(String token) {
        try {
            String checkUrl = configResolver.resolve().getSsoCheckTokenUrl();
            Map<String,String> params = new HashMap<String,String>();
            params.put(ZssoConst.TOKEN,token);
            String result = HttpClientUtil.doPost(checkUrl,params);
            CheckResult checkResult = JsonUtil.fromJson(result,CheckResult.class);
            if (checkResult.getBstatus().getCode() != 0) {
                throw new RuntimeException("check token失败");
            }
            return checkResult.getData();
        }catch (Exception e) {
            return null;
        }
    }
}
