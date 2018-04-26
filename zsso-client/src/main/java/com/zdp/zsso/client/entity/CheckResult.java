package com.zdp.zsso.client.entity;


import com.zdp.zsso.common.entity.Bstatus;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/25
 * Time 下午7:28
 */
public class CheckResult {
    private Bstatus bstatus;
    private CheckResultData data;

    public Bstatus getBstatus() {
        return bstatus;
    }

    public void setBstatus(Bstatus bstatus) {
        this.bstatus = bstatus;
    }

    public CheckResultData getData() {
        return data;
    }

    public void setData(CheckResultData data) {
        this.data = data;
    }
}
