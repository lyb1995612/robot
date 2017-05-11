package com.csjbot.robot.biz.sys.model;

import java.io.Serializable;
import java.util.Date;

import com.csjbot.robot.biz.base.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 
 * 
 * @author cjay
 * 
 */
public class Role implements Serializable {

    private static final long serialVersionUID = -8728329288905261473L;

    private String id = StringUtil.createUUID();

    private String name;

    private String memo;

    private String creator_fk;

    private String updater_fk;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateOfCreate = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateOfUpdate = new Date();

    private boolean valid = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

   
    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Role");
        sb.append("{id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", memo=").append(memo);
        sb.append(", updater_fk=").append(updater_fk);
        sb.append(", creator_fk=").append(creator_fk);
        sb.append(", dateOfCreate=").append(dateOfCreate);
        sb.append(", dateOfUpdate=").append(dateOfUpdate);
        sb.append(", valid=").append(valid);
        sb.append('}');
        return sb.toString();
    }
}