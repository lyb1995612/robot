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

	int insert(ScsDishType scsDishType);

	int delete(Integer id);

	ScsDishType selectByPrimaryKey(Integer id);

	public <E, K, V> PageList<E> page(Map<K, V> params, PageBounds pager);
	
	int updateByPrimaryKeySelective(ScsDishType scsDishType);

    List<ScsDishType> selectAll();
}
