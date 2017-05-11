package com.csjbot.robot.biz.base.entity;

import java.io.Serializable;

public abstract class BaseQueryParam implements Serializable {
    private static final long serialVersionUID = -3434583696021338369L;
    private String keyword;

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
