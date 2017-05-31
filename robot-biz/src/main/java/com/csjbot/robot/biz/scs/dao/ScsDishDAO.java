package com.csjbot.robot.biz.scs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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
	
	int insert(ScsDish scsDish);
	
	int delete(String id);
	
	ScsDish selectByPrimaryKey(String  id);
	
	ScsDish selectByName(String name);
	
	int updateByPrimaryKeySelective(ScsDish scsDish);

	List<ScsDish> selectAll();
	List<ScsDish> selectBySn(String sn);
	
	public <E, K, V> PageList<E> page(Map<K, V> params, PageBounds pager);
}
