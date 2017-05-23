package com.csjbot.robot.biz.scs.dao;


import java.util.List;
import java.util.Map;

import com.csjbot.robot.biz.scs.model.ScsDesk;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 数据访问接口
 * 
 * 
 */
public interface ScsDeskDao {
	
	public final static java.lang.String PREFIX = ScsDeskDao.class.getName();
	
	public int insert(ScsDesk scsDesk);
	
	public int delete(String id);
	
	ScsDesk selectByPrimaryKey(String  id);

	List<ScsDesk> selectByNumber(String number);

	List<ScsDesk> selectAll();

	public <E, K, V> PageList<E> page(Map<K, V> params, PageBounds pager);
}
