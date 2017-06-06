package com.csjbot.robot.biz.scs.model;

import java.util.Date;

import com.csjbot.robot.base.util.StringUtil;

/**
 * @explain 菜品关联
 * @author AlexZhang
 * @date 2017年5月23日
 * @company PangolinRobot
 */
public class ScsDishLink {

	private String id = StringUtil.createUUID();

	private String sn;

	private String dish_id;

	private Byte valid;

	private boolean validT = true;

	private boolean checkedF = false;

	private String creator_fk;

	private String updater_fk;

	private Date date_create;

	private Date date_update;

	public ScsDishLink() {
		super();
	}

	public ScsDishLink(String id, String sn, String dish_id, String dish_name, Byte vaild, boolean validT,
			boolean checkedF, String creator_fk, String updater_fk, Date date_create, Date date_update) {
		super();
		this.id = id;
		this.sn = sn;
		this.dish_id = dish_id;
		this.valid = vaild;
		this.validT = validT;
		this.checkedF = checkedF;
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

	public Byte getValid() {
		return valid;
	}

	public void setValid(Byte valid) {
		this.valid = valid;
	}

	public boolean isValidT() {
		return validT;
	}

	public void setValidT(boolean validT) {
		this.validT = validT;
	}

	public boolean isCheckedF() {
		return checkedF;
	}

	public void setCheckedF(boolean checkedF) {
		this.checkedF = checkedF;
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
