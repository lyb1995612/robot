package com.csjbot.robot.biz.cms.model;

import java.util.Date;

import com.csjbot.robot.base.web.entity.PaginationParam;

public class CmsRobot extends PaginationParam{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String sn;
	
	private Integer type;
	
	private String type_name;
	
	private Byte valid;
	
	private Date register;
	
	private String creator_fk;
	
	private String updater_fk;
	
	private Date date_create;
	
	private Date date_update;
	
	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Byte getValid() {
		return valid;
	}

	public void setValid(Byte valid) {
		this.valid = valid;
	}

	public Date getRegister() {
		return register;
	}

	public void setRegister(Date register) {
		this.register = register;
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

	public CmsRobot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CmsRobot(String id, String sn, Integer type, String type_name, Byte valid, Date register, String creator_fk,
			String updater_fk, Date date_create, Date date_update) {
		super();
		this.id = id;
		this.sn = sn;
		this.type = type;
		this.type_name = type_name;
		this.valid = valid;
		this.register = register;
		this.creator_fk = creator_fk;
		this.updater_fk = updater_fk;
		this.date_create = date_create;
		this.date_update = date_update;
	}

	@Override
	public String toString() {
		return "CmsRobot [id=" + id + ", sn=" + sn + ", type=" + type + ", type_name=" + type_name + ", valid=" + valid
				+ ", register=" + register + ", creator_fk=" + creator_fk + ", updater_fk=" + updater_fk
				+ ", date_create=" + date_create + ", date_update=" + date_update + "]";
	}

}
