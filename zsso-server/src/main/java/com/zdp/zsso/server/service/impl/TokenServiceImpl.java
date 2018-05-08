package com.zdp.zsso.server.service.impl;

import com.zdp.zsso.server.entity.TokenDetail;
import com.zdp.zsso.server.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/5/2
 * Time 下午3:09
 */
@Service
public class TokenServiceImpl implements TokenService {
    private Map<String,TokenDetail> cache = new HashMap();

    @Override
    public TokenDetail create() {
        String token = UUID.randomUUID().toString();
        TokenDetail detail = new TokenDetail();
        detail.setToken(token);
        detail.setExpireTimeMillis(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1));
        return detail;
    }

    @Override
    public TokenDetail detail(String token) {
        return cache.get(token);
    }

    @Override
    public void remove(String token) {
        cache.remove(token);
    }

    @Override
    public void bound(String token, String systemName) {
        detail(token).bound(systemName);
    }

}
