package com.csjbot.robot.biz.sms.dao;


import com.csjbot.robot.biz.sms.model.Sys_data;

public interface Sys_dataDAO {
	 //根据code查询数据
	public abstract Sys_data findCodeById(String code);

}
