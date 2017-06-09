package com.csjbot.robot.biz.scs.dao;

import java.util.List;
import java.util.Map;

import com.csjbot.robot.biz.scs.model.ScsShop;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @explain 店铺信息访问 
 * @author  AlexZhang
 * @date    2017年6月6日
 * @company PangolinRobot
 */
public interface ScsShopDao {
	
	public final static String PREFIX = ScsShopDao.class.getName();
	
	public ScsShop selectByPK(String id); // 主键查询店铺信息
	
	public List<ScsShop> selectAll(); // 查询全部店铺信息
	
	public int delete(String id); // 删除店铺信息
	
	public int insert(ScsShop shop); // 添加店铺信息
	
	public int insertSelective(ScsShop shop); // 条件添加店铺信息
	
	public int update(ScsShop shop); // 更新店铺信息
	
	public int updateSelective(ScsShop shop); // 条件更新店铺信息
	
	public <E, K, V> PageList<E> page(Map<K, V> params, PageBounds pager); // 店铺信息分页
	
}
