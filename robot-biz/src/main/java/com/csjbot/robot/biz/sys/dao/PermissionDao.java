package com.csjbot.robot.biz.sys.dao;

import java.util.List;
import java.util.Map;

import com.csjbot.robot.biz.sys.model.Permission;
import com.csjbot.robot.biz.sys.model.param.PermissionParam;
import com.csjbot.robot.biz.sys.model.result.PermissionResult;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 数据访问接口
 * 
 */
public interface PermissionDao {

    public final static String PREFIX = PermissionDao.class.getName();

    public Permission get(String id);

    public <K, V> Map<K, V> findOne(String id);

    public <T, K, V> List<T> find(Map<K, V> params);

    public int insert(Permission permission);

    public int update(Permission permission);

    public int delete(String id);

    public PageList<PermissionResult> page(PermissionParam param, PageBounds bounds);

}
