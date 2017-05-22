package com.csjbot.robot.biz.pms.dao;

import java.util.Map;

import com.csjbot.robot.biz.pms.model.PmsProduct;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface PmsProductDao {
	
	public final static String PREFIX = PmsProductDao.class.getName();
	
    int deleteByPrimaryKey(String id);

    int insert(PmsProduct record);

    int insertSelective(PmsProduct record);

    PmsProduct selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PmsProduct record);

    int updateByPrimaryKey(PmsProduct record);

	  
    /**     
     * @discription 产品list
     * @author CJay       
     * @created 2017年3月21日 下午1:48:23        
     */
    public <E, K, V> PageList<E> page(Map<K, V> params, PageBounds pager);
}