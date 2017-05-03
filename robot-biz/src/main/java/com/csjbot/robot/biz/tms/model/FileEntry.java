package com.csjbot.robot.biz.tms.model;

import java.util.Date;

public class FileEntry {
	
	private String path;
	private Date uptime;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}
	
}
