package com.csjbot.robot.biz.ums.model.result;

import com.csjbot.robot.biz.ums.model.User;

public class UserResult extends User {

	private static final long serialVersionUID = -2826998909258556078L;
	
	private String creator;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
