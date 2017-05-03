package com.csjbot.robot.base.exception;

public class UtilException extends Exception {
    private static final long serialVersionUID = 793881703856258755L;

    public UtilException(String message) {
        super(message);
    }

    public UtilException(Throwable e) {
        super(e);
    }

    public UtilException(String message, Throwable e) {
        super(message, e);
    }
}
