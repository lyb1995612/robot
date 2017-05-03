package com.csjbot.robot.base.web.entity;

public abstract interface ResultEntity {
    public static final String KW_STATUS_SUCCESS = "S";
    public static final String KW_STATUS_FAIL = "F";

    public abstract void setStatus(String paramString);

    public abstract void setMsg(String paramString);

    public abstract void setResult(Object paramObject);

    public abstract void addObject(String paramString, Object paramObject);

    public abstract void removeObject(String paramString);
}
