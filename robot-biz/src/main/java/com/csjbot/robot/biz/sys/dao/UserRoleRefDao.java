package com.csjbot.robot.biz.sys.dao;

import java.util.List;
import java.util.Map;

import com.csjbot.robot.biz.base.page.Page;
import com.csjbot.robot.biz.sys.model.UserRoleRef;


/**
 * 数据访问接口
 * 
 */
public interface UserRoleRefDao {

    public final static String PREFIX = UserRoleRefDao.class.getName();

    public UserRoleRef get(String id);

    public <K, V> Map<K, V> findOne(String id);

    public <T, K, V> List<T> find(Map<K, V> params);

    public int insert(UserRoleRef ref);

    public int update(UserRoleRef ref);

    public int removeByRoleId(String roleId);

    public int delete(String id);

    public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

    public int countRoleUserSize(String roleId);

    public <T, K, V> List<T> listAdmins(Map<K, V> params);
}
