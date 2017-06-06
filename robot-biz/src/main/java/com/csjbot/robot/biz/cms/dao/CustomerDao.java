package com.csjbot.robot.biz.cms.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.csjbot.robot.biz.cms.model.Customer;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 数据访问接口
 * 
 * 
 */

public interface CustomerDao {
	
	public final static String PREFIX = CustomerDao.class.getName();
	
	public Customer get(@Param("code")String code,@Param("code_group")String code_group);
	
	public <K, V> Map<K, V> findOne(@Param("code")String code,@Param("code_group")String code_group);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(Customer customer);
	
	public int update(Customer customer);
	
	Customer selectByPrimaryKey(@Param("code")String code,@Param("code_group")String code_group);
	
	public int delete(@Param("code")String code,@Param("code_group")String code_group);
	
	public <E, K, V> PageList<E> page(Map<K, V> params, PageBounds pager);
}
