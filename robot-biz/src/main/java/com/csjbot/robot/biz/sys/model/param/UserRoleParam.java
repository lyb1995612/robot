package com.csjbot.robot.biz.sys.model.param;

import com.csjbot.robot.biz.base.entity.PaginationParam;


/**
 * 用户角色查询参数
 * 
 * @author cjay
 * 
 */
public class UserRoleParam extends PaginationParam {

    private static final long serialVersionUID = -87046553835665348L;

    public static final String STATUS_ENABLED = "10";

    public static final String STATUS_DISABLED = "20";

    /**
     * 姓名
     */
    private String username;

    /**
     * 账号
     */
    private String account;

    /**
     * 账户状态
     */
    private String status;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String getStatusEnabled() {
        return STATUS_ENABLED;
    }

    public static String getStatusDisabled() {
        return STATUS_DISABLED;
    }

}