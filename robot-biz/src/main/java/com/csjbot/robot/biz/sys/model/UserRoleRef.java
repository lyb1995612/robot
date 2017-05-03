package com.csjbot.robot.biz.sys.model;

import java.io.Serializable;
import java.util.Date;

import com.csjbot.robot.base.util.StringUtil;


/**
 * 
 * 用户角色关系对象
 * 
 * @author cjay
 * 
 */
public class UserRoleRef implements Serializable {

    private static final long serialVersionUID = 6820208426074983440L;

    private String id = StringUtil.createUUID();

    private String user_fk;

    private String role_fk;

    private String memo;

    /**
     * 是否被选中
     * 
     * @Transient
     * 
     */
    private boolean checked = false;

    private String updater_fk;

    private String creator_fk;

    private Date dateOfCreate = new Date();

    private Date dateOfUpdate = new Date();

    private boolean valid = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_fk() {
        return user_fk;
    }

    public void setUser_fk(String user_fk) {
        this.user_fk = user_fk;
    }

    public String getRole_fk() {
        return role_fk;
    }

    public void setRole_fk(String role_fk) {
        this.role_fk = role_fk;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getUpdater_fk() {
        return updater_fk;
    }

    public void setUpdater_fk(String updater_fk) {
        this.updater_fk = updater_fk;
    }

    public String getCreator_fk() {
        return creator_fk;
    }

    public void setCreator_fk(String creator_fk) {
        this.creator_fk = creator_fk;
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
        sb.append("UserRoleRef");
        sb.append("{id=").append(id);
        sb.append(", user_fk=").append(user_fk);
        sb.append(", role_fk=").append(role_fk);
        sb.append(", memo=").append(memo);
        sb.append(", creator_fk=").append(creator_fk);
        sb.append(", updater_fk=").append(updater_fk);
        sb.append(", dateOfCreate=").append(dateOfCreate);
        sb.append(", dateOfUpdate=").append(dateOfUpdate);
        sb.append(", valid=").append(valid);
        sb.append('}');
        return sb.toString();
    }
}