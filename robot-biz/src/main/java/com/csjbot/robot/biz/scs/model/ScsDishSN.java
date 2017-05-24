package com.csjbot.robot.biz.scs.model;

import java.util.Date;

import com.csjbot.robot.base.util.StringUtil;

/**
 * @explain 菜品绑定数据模型 
 * @author  AlexZhang
 * @date    2017年5月23日
 * @company PangolinRobot
 */
public class ScsDishSN {
	
	private String id = StringUtil.createUUID();

	private String sn;

	private String dish_id;

	private String dish_name;

	private Byte vaild;

	private String creator_fk;

	private String updater_fk;

	private Date date_create;

	private Date date_update;

	public ScsDishSN() {
		super();
	}

	public ScsDishSN(String id, String sn, String dish_id, String dish_name, Byte vaild, String creator_fk,
			String updater_fk, Date date_create, Date date_update) {
		super();
		this.id = id;
		this.sn = sn;
		this.dish_id = dish_id;
		this.dish_name = dish_name;
		this.vaild = vaild;
		this.creator_fk = creator_fk;
		this.updater_fk = updater_fk;
		this.date_create = date_create;
		this.date_update = date_update;
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

	public String getDish_id() {
		return dish_id;
	}

	public void setDish_id(String dish_id) {
		this.dish_id = dish_id;
	}

	public String getDish_name() {
		return dish_name;
	}

	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}

	public Byte getVaild() {
		return vaild;
	}

	public void setVaild(Byte vaild) {
		this.vaild = vaild;
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

}
