package com.csjbot.robot.biz.sys.model;

import java.io.Serializable;
import java.util.Date;

import com.csjbot.robot.biz.base.util.StringUtil;

/**
 * 
 * sys_role_permission_ref表
 * 
 * @author cjay
 * 
 */
public class RolePermissionRef implements Serializable {

    private static final long serialVersionUID = -7117351502582227383L;

    private String id = StringUtil.createUUID();

    /**
     * 角色ID
     */
    private String role_fk;

    /**
     * 资源ID
     */
    private String resource_fk;

    /**
     * 权限ID
     */
    private String permission_fk;

    private String memo;

    private String updater_fk;

    private String creator_fk;

    private Date dateOfCreate = new Date();

    private Date dateOfUpdate = new Date();

    private boolean isValid = true;
    
    private boolean checked = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole_fk() {
        return role_fk;
    }

    public void setRole_fk(String role_fk) {
        this.role_fk = role_fk;
    }

    public String getResource_fk() {
        return resource_fk;
    }

    public void setResource_fk(String resource_fk) {
        this.resource_fk = resource_fk;
    }

    public String getPermission_fk() {
        return permission_fk;
    }

    public void setPermission_fk(String permission_fk) {
        this.permission_fk = permission_fk;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
    
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("RolePermissionRef");
        sb.append("{id=").append(id);
        sb.append(", role_fk=").append(role_fk);
        sb.append(", resource_fk=").append(resource_fk);
        sb.append(", permission_fk=").append(permission_fk);
        sb.append(", creator_fk=").append(creator_fk);
        sb.append(", updater_fk=").append(updater_fk);
        sb.append(", memo=").append(memo);
        sb.append(", dateOfCreate=").append(dateOfCreate);
        sb.append(", dateOfUpdate=").append(dateOfUpdate);
        sb.append(", isValid=").append(isValid);
        sb.append('}');
        return sb.toString();
    }
}