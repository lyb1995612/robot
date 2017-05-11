/**
 * 
 */
package com.csjbot.robot.biz.sms.dao;


import com.csjbot.robot.biz.base.BaseDAO;
import com.csjbot.robot.biz.sms.model.Sms_group;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author 作者：Zhangyangyang
 * @version 创建时间：2017年3月27日 下午4:40:50
 * 类说明
 */
public interface Sms_groupDAO extends BaseDAO<Sms_group> {
	
	//根据master账号查询组
	public abstract List<Sms_group> findGroupByAccount(String account);
	
	//根据name查询组
	public abstract List<Sms_group> findGroupByName(String name);
	
	//根据账号和名称查询组
	public abstract Sms_group findGroupByNA(@Param("account") String account, @Param("name") String name);
}
