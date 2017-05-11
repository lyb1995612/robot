package com.csjbot.robot.biz.ums.model.param;

import com.csjbot.robot.biz.base.entity.PaginationParam;

/**
 * 
 * @author Cjay
 *
 */
public class UserAdminSearchParam extends PaginationParam {

    private static final long serialVersionUID = 1587610599229451835L;
    // 姓名
    private String realname;
    // 用户名
    private String username;
    // 区域代码
    private String area_fk;
    // 是否是省
    private String area_isPro;
    // 理财师姓名
    private String financial;
    // 账户类型
    private int type;
    // 推荐码
    private String recommendedCode;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUsername() {
        return username;
    }

    public String getArea_isPro() {
        return area_isPro;
    }

    public void setArea_isPro(String area_isPro) {
        this.area_isPro = area_isPro;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea_fk() {
        return area_fk;
    }

    public void setArea_fk(String area_fk) {
        this.area_fk = area_fk;
    }

    public String getFinancial() {
        return financial;
    }

    public void setFinancial(String financial) {
        this.financial = financial;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRecommendedCode() {
        return recommendedCode;
    }

    public void setRecommendedCode(String recommendedCode) {
        this.recommendedCode = recommendedCode;
    }

}
