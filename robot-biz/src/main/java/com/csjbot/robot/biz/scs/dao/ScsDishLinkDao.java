package com.csjbot.robot.biz.scs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.csjbot.robot.biz.scs.model.ScsDishLink;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @explain 菜品绑定：每一个SN下，有一个或多个菜品名
 * @author  AlexZhang
 * @date    2017年5月23日
 * @company PangolinRobot
 */
public interface ScsDishLinkDao {

	public int countType(@Param("sn") String sn, @Param("dish_name") String dish_name);

	public ScsDishLink selectByPK(String id);
	
	public ScsDishLink getDish(String id);

	public List<ScsDishLink> selectBySN(String sn);
	
	public List<String> selectSN();

	public List<Map<String, Object>> listDishSN(Map<String, Object> params);

	public List<ScsDishLink> selectAll();
	
	public <E, K, V> PageList<E> page(Map<K, V> params, PageBounds pager);
	
	public int countDishLinkSize(String dishId);
	
	public List<Map<String, Object>> listDish(Map<String, Object> params);
	
	public int insert(ScsDishLink scsDishLink);

    public int update(ScsDishLink scsDishLink);

}
