package com.csjbot.robot.biz.scs.model;
/**
 * 
 * @author Zhangyangyang
 * 
 */

import java.sql.Timestamp;

public class ScsDishType {
	private int id;
	private String type_name;
	private String updater_fk;
	private String creator_fk;
	private Timestamp date_update;
	private Timestamp date_create;
	
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Timestamp getDate_update() {
		return date_update;
	}
	public void setDate_update(Timestamp date_update) {
		this.date_update = date_update;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	@Override
	public String toString() {
		return "ScsDishType [id=" + id + ", type_name=" + type_name + ", updater_fk=" + updater_fk + ", creator_fk="
				+ creator_fk + ", date_update=" + date_update + ", date_create=" + date_create + "]";
	}
	
	
}
