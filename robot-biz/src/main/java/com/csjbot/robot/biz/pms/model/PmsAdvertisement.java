package com.csjbot.robot.biz.pms.model;

import java.util.Date;

import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.PaginationParam;

/**
 * @describe 广告信息
 * @author   AlexZhang
 * @date     2017年4月24日
 * @company  PangolinRobot
 */
public class PmsAdvertisement extends PaginationParam{
    
	private static final long serialVersionUID = 3897803769326567059L;

	private String id = StringUtil.createUUID();  // 主键。默认为自定义

    private String name; // 广告名称

    private int type; // 广告类型
    
    private Byte valid = 1; // 有效标记。默认为1

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}