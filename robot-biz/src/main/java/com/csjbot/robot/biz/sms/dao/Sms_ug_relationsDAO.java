package com.csjbot.robot.biz.sms.dao;


import com.csjbot.robot.biz.base.BaseDAO;
import com.csjbot.robot.biz.sms.model.Sms_ug_relations;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 作者：Zhangyangyang
 * @version 创建时间：2017年3月27日 下午4:43:32
 * 类说明
 */
public interface Sms_ug_relationsDAO extends BaseDAO<Sms_ug_relations> {
	//根据group_id查询组关系
	public abstract List<Sms_ug_relations> findRelationByGid(Integer group_id);
	
	//根据账号查询组关系
	public abstract List<Sms_ug_relations> findRelationByAccount(String account);
	
	//根据group_id和账号查询关系
	public abstract Sms_ug_relations findRelationByGidAcc(@Param("account") String account, @Param("group_id") Integer group_id);
}
