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
	
	public int deleteByPrimaryKey(String id);
    
	public int seleteByPrimaryKey(String id);

    public int insert(ScsAccessory record);

    public int insertSelective(ScsAccessory record);
    
    public ScsAccessory selectByPrimaryKey(String id);

    public ScsAccessory Accessory(String id);
    
    public List<ScsAccessory> selectAll();
    
    public List<ScsAccessory> selectBySn(Map<String,String> params);
	 
    public <E, K, V> PageList<E> page(Map<K, V> params, PageBounds pager);

}
