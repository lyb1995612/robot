package com.csjbot.robot.biz.cms.model;

import java.util.Date;

import com.csjbot.robot.base.web.entity.PaginationParam;

public class CmsRobotGroup extends PaginationParam{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String group_name;
	
    private Integer type;
    
    private String type_name;
    
    private Integer relevance_num;
	
	private Byte valid;
	
	private String creator_fk;
	
	private String updater_fk;
	
	private Date date_create;
	
	private Date date_update;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	public Integer getRelevance_num() {
		return relevance_num;
	}

	public void setRelevance_num(Integer relevance_num) {
		this.relevance_num = relevance_num;
	}

	public Byte getValid() {
		return valid;
	}

	public void setValid(Byte valid) {
		this.valid = valid;
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

	public CmsRobotGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CmsRobotGroup(String id, String group_name, Integer type, String type_name, Integer relevance_num,
			Byte valid, String creator_fk, String updater_fk, Date date_create, Date date_update) {
		super();
		this.id = id;
		this.group_name = group_name;
		this.type = type;
		this.type_name = type_name;
		this.relevance_num = relevance_num;
		this.valid = valid;
		this.creator_fk = creator_fk;
		this.updater_fk = updater_fk;
		this.date_create = date_create;
		this.date_update = date_update;
	}
    
}
