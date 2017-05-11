/**
 * 
 */
package com.csjbot.robot.biz.frs.dao;


import com.csjbot.robot.biz.base.BaseDAO;
import com.csjbot.robot.biz.frs.model.Frs_person;

import java.util.List;

/**
 * @author 作者：Zhangyangyang
 * @version 创建时间：2017年3月27日 上午9:54:41 类说明
 */
public interface Frs_personDAO extends BaseDAO<Frs_person> {
	
	// 根据person_name查询用户
	public abstract Frs_person findPersonByName(String name);

	// 根据person_id查询用户
	public abstract Frs_person findPersonByperId(String person_id);

	// 根据group_id查询用户
	public abstract List<Frs_person> findPersonByGroupId(String group_id);
}
