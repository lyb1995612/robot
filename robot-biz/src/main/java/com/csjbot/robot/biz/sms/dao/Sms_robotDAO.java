/**
 * 
 */
package com.csjbot.robot.biz.sms.dao;


import com.csjbot.robot.biz.base.BaseDAO;
import com.csjbot.robot.biz.sms.model.Sms_robot;

import java.util.List;

/**
 * @author 作者：Zhangyangyang
 * @version 创建时间：2017年3月27日 下午4:39:49
 * 类说明
 */
public interface Sms_robotDAO extends BaseDAO<Sms_robot> {
	//根据uid查询机器人
	public abstract Sms_robot findRobotByUid(String uid);
	
	//根据主人账号查询机器人
	public abstract Sms_robot findRobotByAccount(String masterAccount);
	
	//模糊查询
	public abstract List<Sms_robot> findRobotByStr(String str);
}
