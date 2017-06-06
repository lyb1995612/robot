package com.csjbot.robot.biz.scs.dao;

import java.util.List;
import java.util.Map;

import com.csjbot.robot.biz.scs.model.ScsDishType;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 菜品类型接口
 * 
 * @author Zhangyangyang
 *
 */
public interface ScsDishTypeDAO {
	
	public final static java.lang.String PREFIX = ScsDishTypeDAO.class.getName();

	public int insert(ScsDishType scsDishType);

	public int delete(Integer id);

	public ScsDishType selectByPrimaryKey(Integer id);

	public <E, K, V> PageList<E> page(Map<K, V> params, PageBounds pager);
	
	public int updateByPrimaryKeySelective(ScsDishType scsDishType);

	public List<ScsDishType> selectAll();
    
	public List<ScsDishType> selectBySn(String sn);
	
}
