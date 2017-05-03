package com.csjbot.robot.biz.sys.dao;

import java.util.Map;

import com.csjbot.robot.base.page.Page;
import com.csjbot.robot.biz.sys.model.SysVersionRobot;

public interface SysVersionRobotDao {
	
	public final static String PREFIX = SysVersionRobotDao.class.getName();
	
    int deleteByPrimaryKey(String id);

    int insert(SysVersionRobot record);

    int insertSelective(SysVersionRobot record);

    SysVersionRobot selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysVersionRobot record);

    int updateByPrimaryKey(SysVersionRobot record);
    
    public <E, K, V> Page<E> pageAndSort(Map<String, Object> params,int current, int pagesize, String sortString);
}