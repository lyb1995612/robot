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
	
	public int insertRobotDeskRef(Map<String,Object> params);
	
	public int delete(String id);
	
	public int deleteBySn(String sn);
	
	public int deleteRobotDeskRefBySn(String sn);
	
	ScsDesk selectByPrimaryKey(String  id);

	List<ScsDesk> selectByNumber(String number);

	List<ScsDesk> selectAll();
	
	List<ScsDesk> selectBySn(String sn);

	public <E, K, V> PageList<E> page(Map<K, V> params, PageBounds pager);
}
