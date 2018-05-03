package com.zdp.zsso.server.service;

import java.util.List;

public interface TokenService {
    void bound(String token,String systemName);

    List<String> listSystemNames(String token);
}
