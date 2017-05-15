package com.csjbot.robot.biz.cms.dao;

import java.util.List;
import java.util.Map;

import com.csjbot.robot.biz.cms.model.CmsRobot;
import com.csjbot.robot.biz.pms.model.PmsProduct;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 数据访问接口
 * 
 * 
 */
public interface CmsRobotDao {
	
	public int insert(CmsRobot record);
	
	CmsRobot selectByPrimaryKey(String id);
	
	public int updateByPrimaryKey(CmsRobot record);
	
	public int delete(String id);
	
	<T, K, V> List<T> getRobotGroupRef(Map<String, Object> params);
	
	public PageList<CmsRobot> page(Map<String, Object> params, PageBounds pager);

}
