package com.zdp.zsso.server.service.impl;

import com.zdp.zsso.server.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/5/2
 * Time 下午3:09
 */
@Service
public class TokenServiceImpl implements TokenService {
    private MultiValueMap<String,String> cache = new LinkedMultiValueMap();
    @Override
    public void bound(String token, String systemName) {
        cache.add(token,systemName);
    }

    @Override
    public List<String> listSystemNames(String token) {
        return cache.get(token);
    }
}
