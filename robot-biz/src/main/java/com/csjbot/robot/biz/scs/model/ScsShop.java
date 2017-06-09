package com.csjbot.robot.biz.scs.model;

import java.util.Date;

import com.csjbot.robot.base.util.StringUtil;

/**
 * @explain 店铺信息
 * @author AlexZhang
 * @date 2017年6月5日
 * @company PangolinRobot
 */
public class ScsShop {

	private String id = StringUtil.createUUID();; // 主键

	private String shop_name; // 店铺名称

	private String shop_code; // 店铺编号

	private String shop_address; // 店铺地址

	private int valid = 1; // 有效标记。默认为1

	private String memo; // 备注

	private String creator_fk; // 创建者

	private String updater_fk; // 更新者

	private Date date_create; // 创建日期

	private Date date_update; // 更新日期

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getShop_code() {
		return shop_code;
	}

	public void setShop_code(String shop_code) {
		this.shop_code = shop_code;
	}

	public String getShop_address() {
		return shop_address;
	}

	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
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

}
