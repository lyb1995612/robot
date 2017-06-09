package com.csjbot.robot.biz.cms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.csjbot.robot.biz.cms.model.CmsRobot;
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
	
	public List<String> selectAllSN();
	
	public CmsRobot selectBySN(@Param("sn")String sn);
	
	<T, K, V> List<T> getRobotGroupRef(Map<String, Object> params);
	
	public <E, K, V> PageList<E> page(Map<K, V> params, PageBounds pager);
	
	public List<CmsRobot> getCmsRobots();
	
	public List<CmsRobot> getAdCmsRobots();

}
