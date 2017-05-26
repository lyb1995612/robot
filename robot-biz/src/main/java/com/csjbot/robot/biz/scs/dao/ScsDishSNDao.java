package com.csjbot.robot.biz.scs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.csjbot.robot.biz.scs.model.ScsDishSN;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @explain 菜品绑定信息：每一个SN下，有一个或多个菜品名
 * @author  AlexZhang
 * @date    2017年5月23日
 * @company PangolinRobot
 */
public interface ScsDishSNDao {

	public int insert(ScsDishSN scsDishSN);

	public int countType(@Param("sn") String sn, @Param("dish_name") String dish_name);

	public ScsDishSN selectByPrimaryKey(String id);

	public ScsDishSN selectBySN(String sn);
	
	public List<String> selectSN();

	public List<Map<String, Object>> listDishSN(Map<String, Object> params);

	public List<ScsDishSN> selectAll();
	
	public <E, K, V> PageList<E> page(Map<K, V> params, PageBounds pager);

}
