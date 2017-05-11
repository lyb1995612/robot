/**
 * 
 */
package com.csjbot.robot.biz.sms.dao;


import com.csjbot.robot.biz.base.BaseDAO;
import com.csjbot.robot.biz.sms.model.Sms_admin;

/**
 * @author 作者：Zhangyangyang
 * @version 创建时间：2017年3月30日 下午1:50:59
 * 类说明
 */
public interface Sms_adminDAO extends BaseDAO<Sms_admin> {
	//根据uid查询用户
	public abstract Sms_admin findAdminByUid(String uid);
	
	//根据账号查询用户
	public abstract Sms_admin findAdminByAccount(String account);

}
