package com.csjbot.robot.biz.sys.model.param;

import java.util.Date;

import com.csjbot.robot.base.web.entity.PaginationParam;

public class PermissionParam extends PaginationParam {
    /**
     * 
     */
    private static final long serialVersionUID = -3619146372208285929L;
    
    private String id;
    private String code;
    private String name;
    private String memo;
    private int type = -1;
    private Date dateOfCreate;
    private Date dateOfUpdate;
    private int isValid = -1;
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
}
