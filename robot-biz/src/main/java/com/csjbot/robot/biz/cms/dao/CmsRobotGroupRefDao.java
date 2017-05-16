package com.csjbot.robot.biz.cms.dao;

import com.csjbot.robot.biz.cms.model.CmsRobotGroupRef;

/**
 * 数据访问接口
 * 
 * 
 */
public interface CmsRobotGroupRefDao {
	
	public int countRobotGroupSize(String groupId);
	
	public CmsRobotGroupRef get(String id);
	
	public int insert(CmsRobotGroupRef ref);

    public int update(CmsRobotGroupRef ref);
    
    public CmsRobotGroupRef delete(String id);
	
}
