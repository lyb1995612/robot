package com.csjbot.robot.biz.cms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.csjbot.robot.biz.cms.model.CmsRobotGroup;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 数据访问接口
 * 
 * 
 */
public interface CmsRobotGroupDao {
	
    public int insert(CmsRobotGroup record);
	
    CmsRobotGroup selectByPrimaryKey(String id);
    
    public int countType(@Param("type")Integer type,@Param("group_name")String group_name);
    
    CmsRobotGroup selectByGroupName(String group_name);
    
    public List<Map<String, Object>> listRobot(Map<String, Object> params);
    
	public PageList<CmsRobotGroup> page(Map<String, Object> params, PageBounds pager);

}
