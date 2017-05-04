package com.csjbot.robot.biz.scs.dao;


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
	
	public PageList<ScsDesk> page(ScsDesk param, PageBounds bounds);
}
