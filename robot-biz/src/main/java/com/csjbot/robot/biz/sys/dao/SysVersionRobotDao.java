package com.csjbot.robot.biz.sys.dao;

import java.util.Map;

import com.csjbot.robot.biz.sys.model.SysVersionRobot;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SysVersionRobotDao {
	
	public final static String PREFIX = SysVersionRobotDao.class.getName();
	
    int deleteByPrimaryKey(String id);

    int insert(SysVersionRobot record);

    int insertSelective(SysVersionRobot record);

    SysVersionRobot selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysVersionRobot record);

    int updateByPrimaryKey(SysVersionRobot record);
    
    public PageList<SysVersionRobot> page(Map<String, Object> params, PageBounds pager);
}