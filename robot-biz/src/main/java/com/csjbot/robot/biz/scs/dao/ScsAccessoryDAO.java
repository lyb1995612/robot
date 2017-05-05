package com.csjbot.robot.biz.scs.dao;

import java.util.List;
import java.util.Map;

import com.csjbot.robot.biz.scs.model.ScsAccessory;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 数据访问接口
 * 
 * 
 */

public interface ScsAccessoryDAO {
	public final static String PREFIX = ScsAccessoryDAO.class.getName();
	
    int deleteByPrimaryKey(String id);
    
    int seleteByPrimaryKey(String id);

    int insert(ScsAccessory record);

    int insertSelective(ScsAccessory record);
    
    ScsAccessory selectByPrimaryKey(String id);

    ScsAccessory Accessory(String id);
    
    List<ScsAccessory> selectAll();
	 
    public PageList<ScsAccessory> page(Map<String, Object> params, PageBounds bounds);
}
