package com.csjbot.robot.biz.base.model;

import java.io.Serializable;

public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = -6832179320732494113L;
    protected short isValid;

    public BaseModel() {
        this.isValid = 1;
    }

    public short getIsValid() {
        return this.isValid;
    }

    public void setIsValid(short isValid) {
        this.isValid = isValid;
    }
}