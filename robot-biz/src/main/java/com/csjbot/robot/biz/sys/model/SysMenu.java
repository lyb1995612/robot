package com.csjbot.robot.biz.sys.model;

import java.io.Serializable;
import java.util.Date;

public class SysMenu implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2156966114236754981L;
    
    private String id;            // 主键
    private String name;          // 菜单名称
    private String style;         // 菜单样式(菜单图标)
    private String parent_fk;     // 父级菜单外键
    private String permission_fk; // 权限外键
    private String url;            // 访问链接
    private String level;          // 菜单层级
    private int sorter = 0;        // 排序
    private String activeId;       // 菜单打开标识
    private int valid = 1;         // 是否有效
    private String updater_fk;     // 更新者
    private String creator_fk;     // 创建者
    private Date date_update;      // 更新日期
    private Date date_create;      // 创建日期
    
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
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }
    public String getParent_fk() {
        return parent_fk;
    }
    public void setParent_fk(String parent_fk) {
        this.parent_fk = parent_fk;
    }
    public String getPermission_fk() {
        return permission_fk;
    }
    public void setPermission_fk(String permission_fk) {
        this.permission_fk = permission_fk;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public int getSorter() {
        return sorter;
    }
    public void setSorter(int sorter) {
        this.sorter = sorter;
    }
    public String getActiveId() {
        return activeId;
    }
    public void setActiveId(String activeId) {
        this.activeId = activeId;
    }
    public int getValid() {
        return valid;
    }
    public void setValid(int valid) {
        this.valid = valid;
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
    public Date getDate_update() {
        return date_update;
    }
    public void setDate_update(Date date_update) {
        this.date_update = date_update;
    }
    public Date getDate_create() {
        return date_create;
    }
    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }
    
}
