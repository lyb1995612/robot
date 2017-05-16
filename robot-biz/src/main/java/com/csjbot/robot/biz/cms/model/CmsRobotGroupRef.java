package com.csjbot.robot.biz.cms.model;

import java.util.Date;

import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.PaginationParam;

public class CmsRobotGroupRef extends PaginationParam{
	
	private static final long serialVersionUID = 1L;

	private String id = StringUtil.createUUID();
	
	private String group_fk;
	
	private String sn;
	
	private String memo;
	
	private boolean isValid = true;
	
	private String creator_fk;
	
	private String updater_fk;
	
	private Date date_create = new Date();
	
	private Date date_update = new Date();
	
	private boolean checked = false;
    
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroup_fk() {
		return group_fk;
	}

	public void setGroup_fk(String group_fk) {
		this.group_fk = group_fk;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
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

	public Date getDate_create() {
		return date_create;
	}

	public void setDate_create(Date date_create) {
		this.date_create = date_create;
	}

	public Date getDate_update() {
		return date_update;
	}

	public void setDate_update(Date date_update) {
		this.date_update = date_update;
	}
	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public CmsRobotGroupRef() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CmsRobotGroupRef(String id, String group_fk, String sn, String memo, boolean isValid, String creator_fk,
			String updater_fk, Date date_create, Date date_update, boolean checked) {
		super();
		this.id = id;
		this.group_fk = group_fk;
		this.sn = sn;
		this.memo = memo;
		this.isValid = isValid;
		this.creator_fk = creator_fk;
		this.updater_fk = updater_fk;
		this.date_create = date_create;
		this.date_update = date_update;
		this.checked = checked;
	}




	
	
}
