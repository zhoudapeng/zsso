package com.zdp.zsso.server.service;

public interface UserService {
    boolean bound(String token,String userId);

    boolean unbound(String token);

    String query(String token);
}
