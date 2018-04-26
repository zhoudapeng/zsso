package com.zdp.zsso.client.component.impl;

import com.zdp.zsso.client.component.UrlHelper;
import com.zdp.zsso.client.component.ZssoClient;
import com.zdp.zsso.client.entity.CheckResult;
import com.zdp.zsso.client.entity.CheckResultData;
import com.zdp.zsso.common.util.HttpClientUtil;
import com.zdp.zsso.common.util.JsonUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/26
 * Time 下午7:07
 */
@Component
public class ZssoClientImpl implements ZssoClient{
    @Resource
    private UrlHelper urlHelper;

    @Override
    public CheckResultData check(String token) {
        try {
            String checkUrl = urlHelper.getCheckUrl(token);
            String result = HttpClientUtil.doGet(checkUrl);
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
