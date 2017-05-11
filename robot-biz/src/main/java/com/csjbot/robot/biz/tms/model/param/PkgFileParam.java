package com.csjbot.robot.biz.tms.model.param;

import java.util.Date;

import com.csjbot.robot.biz.base.entity.PaginationParam;

public class PkgFileParam extends PaginationParam{

	private static final long serialVersionUID = 2793241266211761509L;

	private Long id;

	private String name;

	private String path;

	private Date uploadTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

}
