package com.csjbot.robot.base.web.entity;

import java.util.HashMap;
import java.util.List;

public class ResultEntityHashMapImpl extends HashMap<String, Object> implements
        ResultEntity {
    private static final long serialVersionUID = -2609985704621734746L;
    protected static final String KW_KEY_STATUS = "status";
    protected static final String KW_KEY_MSG = "message";
    protected static final String KW_KEY_RESULT = "result";
    protected static final String KW_KEY_LIST = "list";

    public ResultEntityHashMapImpl() {
    }

    public ResultEntityHashMapImpl(String status, String msg) {
        put("status", status);
        put("message", msg);
    }

    public ResultEntityHashMapImpl(String status, String msg, Object entity) {
        put("status", status);
        put("message", msg);
        if (entity == null)
            return;
        put((entity instanceof List) ? "list" : "result", entity);
    }

    public void setStatus(String status) {
        put("status", status);
    }

    public void setMsg(String msg) {
        put("message", msg);
    }

    public void setResult(Object result) {
        put("result", result);
    }

    public void addObject(String key, Object value) {
        put(key, value);
    }

    public void removeObject(String key) {
        remove(key);
    }
}
