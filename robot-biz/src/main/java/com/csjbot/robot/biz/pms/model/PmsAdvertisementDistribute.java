package com.csjbot.robot.biz.pms.model;

import java.util.Date;

public class PmsAdvertisementDistribute {

	/**  描述   (@author: lyb) */      

	private String id;

    private String sn;

    private int number;

    private String advertise_fk;

    private int valid = 1;

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

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getAdvertise_fk() {
		return advertise_fk;
	}

	public void setAdvertise_fk(String advertise_fk) {
		this.advertise_fk = advertise_fk;
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
