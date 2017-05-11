package com.csjbot.robot.biz.base.exception;

import org.apache.shiro.authc.AuthenticationException;

public class DisableAccountException extends AuthenticationException {
    private static final long serialVersionUID = 793881703856258755L;

    public DisableAccountException() {
    }

    public DisableAccountException(String message) {
        super(message);
    }

    public DisableAccountException(Throwable e) {
        super(e);
    }

    public DisableAccountException(String message, Throwable e) {
        super(message, e);
    }
}
