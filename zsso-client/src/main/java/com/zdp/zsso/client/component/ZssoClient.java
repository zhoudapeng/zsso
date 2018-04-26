package com.zdp.zsso.client.component;

import com.zdp.zsso.client.entity.CheckResultData;

public interface ZssoClient {
    CheckResultData check(String token);
}
