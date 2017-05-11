package com.csjbot.robot.biz.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.base.exception.ServiceException;
import com.csjbot.robot.biz.base.util.StringUtil;
import com.csjbot.robot.biz.sys.dao.SysMenuDao;
import com.csjbot.robot.biz.sys.model.SysMenu;



@Service
public class SysMenuService{
    
    @Autowired
    private SysMenuDao sysMenuDao;
    
    public int insert(SysMenu menu) throws ServiceException {
        menu.setId(StringUtil.createUUID());
        return sysMenuDao.insert(menu);
    }

    
    public int update(Map<String, Object> params) throws ServiceException{
        return sysMenuDao.update(params);
    }

    
    public SysMenu findOne(String id) {
        return sysMenuDao.findOne(id);
    }

    
    public <E> List<E> listAll(Map<String, Object> params) throws ServiceException{
        return sysMenuDao.listAll(params);
    }

    
    public int delete(String id) {
        return sysMenuDao.delete(id);
    }

    
    public <K, V> List<Map< K, V>> getMenuPermissions() {
        return sysMenuDao.getMenuPermissions();
    }

    
    public <K, V> List<Map<K, V>> getMenuParents() {
        return sysMenuDao.getMenuParents();
    }

    
    public Map<String, Object> getMenuPermissionById(String id) {
        return sysMenuDao.getMenuPermissionById(id);
    }

}
