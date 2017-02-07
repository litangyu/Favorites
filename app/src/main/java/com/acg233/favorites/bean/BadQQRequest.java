package com.acg233.favorites.bean;

import java.util.List;

/**
 * Describe
 * <p>Version: v1.0</p>
 * <p>Created by: lty</p>
 * <p>Created on: 2017/2/6 下午1:53</p>
 * <p>Email: lty81372860@sina.com</p>
 * <p>Copyright © 2017年 lty. All rights reserved.</p>
 * <p>Revision：</p>
 */

public class BadQQRequest extends BaseRequest {
    private String bad;
    private List<Long> qq;

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }

    public List<Long> getQq() {
        return qq;
    }

    public void setQq(List<Long> qq) {
        this.qq = qq;
    }
}
