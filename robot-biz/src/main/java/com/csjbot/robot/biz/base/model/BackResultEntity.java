package com.csjbot.robot.biz.base.model;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BackResultEntity extends HashMap<String, Object> {

    private static final long serialVersionUID = -4803724090820231421L;

    protected static final String KW_KEY_STATUS = "status";
    protected static final String KW_KEY_MSG = "message";
    protected static final String KW_KEY_RESULT = "result";

    public static final int STATUS_SUCCESS = 0;

    public static final int STATUS_ERROR = 1;

    public BackResultEntity() {
    }

    public BackResultEntity(int status, String message) {
        this.put(KW_KEY_STATUS, status);
        this.put(KW_KEY_MSG, message);
    }

    public BackResultEntity(int status, String message, Object result) {
        this.put(KW_KEY_STATUS, status);
        this.put(KW_KEY_MSG, message);
        if (result != null) this.put(KW_KEY_RESULT, result);
    }

    public int getStatus() {
        return (Integer) this.get(KW_KEY_STATUS);
    }

    public String getMessage() {
        return (String) this.get(KW_KEY_MSG);
    }

    public Object getResult() {
        return this.get(KW_KEY_RESULT);
    }

    public void addObject(String key, Object value) {
        this.put(key, value);
    }

    public void removeObject(String key) {
        this.remove(key);
    }

}
