package com.csjbot.robot.biz.scs.dao;

import java.util.List;
import java.util.Map;

import com.csjbot.robot.biz.scs.model.ScsDish;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 菜品接口
 * @author Zhangyangyang
 *
 */
public interface ScsDishDAO {
	
	public final static java.lang.String PREFIX = ScsDishDAO.class.getName();
	
	public int insert(ScsDish scsDish);
	
	public int delete(String id);
	
	public ScsDish selectByPrimaryKey(String  id);
	
	public ScsDish selectByName(String name);
	
	public int updateByPrimaryKeySelective(ScsDish scsDish);

	public List<ScsDish> selectAll();
	
	public List<ScsDish> selectBySn(String sn);
	
	public <E, K, V> PageList<E> page(Map<K, V> params, PageBounds pager);
	
}
