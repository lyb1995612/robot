package com.csjbot.robot.biz.sys.dao;

import java.util.List;
import java.util.Map;

import com.csjbot.robot.biz.sys.model.SysMenu;

public interface SysMenuDao {
    
    int insert(SysMenu menu);
    
    int update(Map<String, Object> params);
    
    SysMenu findOne(String id);
    
    <E> List<E> listAll(Map<String, Object> params);
    
    int delete(String id);
    
    <E> List<E> getMenuPermissions();
    
    <E> List<E> getMenuParents();
    
    Map<String, Object> getMenuPermissionById(String id);
}
