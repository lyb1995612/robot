package com.csjbot.robot.biz.pms.model;

import java.util.Date;

import com.csjbot.robot.base.web.entity.PaginationParam;

public class PmsAdvertisement extends PaginationParam{
    
		/**  描述   (@author: CJay) */      
	    
	private static final long serialVersionUID = 3897803769326567059L;

	private String id;

    private String name;

    private String memo;
    
    private int type;

	private Byte valid;

    private String updater_fk;

    private String creator_fk;

    private Date date_update;

    private Date date_create;

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

    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Byte getValid() {
        return valid;
    }

    public void setValid(Byte valid) {
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