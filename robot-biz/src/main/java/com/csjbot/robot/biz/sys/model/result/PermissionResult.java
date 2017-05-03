package com.csjbot.robot.biz.sys.model.result;

import com.csjbot.robot.biz.sys.model.Permission;

public class PermissionResult extends Permission{

	private static final long serialVersionUID = 5334509687592018629L;
	
	private String status;
	private String typeName;
	private String creator;
	private String updater;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	
}
