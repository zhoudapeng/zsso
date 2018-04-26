package com.zdp.zsso.client.component;

import com.zdp.zsso.client.entity.User;

public interface UserStore {
    User resolve(String token);

    void store(String token, String userId);

    void unbound(String token);
}
