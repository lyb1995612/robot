package com.csjbot.robot.biz.sys.model;

import java.io.Serializable;
import java.util.Date;

import com.csjbot.robot.base.util.StringUtil;

public class Permission implements Serializable {

    private static final long serialVersionUID = -4135478165368593061L;

    private String id = StringUtil.createUUID();

    private String code;

    private String name;

    private String memo;
    
    private int type;

    private String creator_fk;
    
    private String updater_fk;
    
    private Date dateOfCreate;

    private Date dateOfUpdate;

    private int isValid = 1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator_fk() {
        return creator_fk;
    }

    public void setCreator_fk(String creator_fk) {
        this.creator_fk = creator_fk;
    }

    public String getUpdater_fk() {
        return updater_fk;
    }

    public void setUpdater_fk(String updater_fk) {
        this.updater_fk = updater_fk;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public Date getDateOfUpdate() {
        return dateOfUpdate;
    }

    public void setDateOfUpdate(Date dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    
    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Permission");
        sb.append("{id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", memo=").append(memo);
        sb.append(", dateOfCreate=").append(dateOfCreate);
        sb.append(", dateOfUpdate=").append(dateOfUpdate);
        sb.append(", isValid=").append(isValid);
        sb.append('}');
        return sb.toString();
    }
}