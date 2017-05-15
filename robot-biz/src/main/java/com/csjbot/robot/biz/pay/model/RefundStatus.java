package com.csjbot.robot.biz.pay.model;

public enum RefundStatus {
    PRE,
    WAIT,
    SUCCESS,
    FAIL;

    public static boolean isSuccess(String val) {
        return SUCCESS.name().equals(val);
    }
}
