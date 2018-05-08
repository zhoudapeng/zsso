package com.zdp.zsso.server.service;

import com.zdp.zsso.server.entity.TokenDetail;

public interface TokenService {
    TokenDetail create();

    TokenDetail detail(String token);

    void remove(String token);

    void bound(String token,String systemName);
}
