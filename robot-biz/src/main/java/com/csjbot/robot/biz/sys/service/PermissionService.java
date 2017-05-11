package com.csjbot.robot.biz.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.base.exception.ServiceException;
import com.csjbot.robot.biz.base.util.StringUtil;
import com.csjbot.robot.biz.sys.dao.PermissionDao;
import com.csjbot.robot.biz.sys.model.Permission;
import com.csjbot.robot.biz.sys.model.param.PermissionParam;
import com.csjbot.robot.biz.sys.model.result.PermissionResult;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


@Service
public class PermissionService  {
    
    @Autowired
    private PermissionDao permissionDao;
    
    
    public Permission get(String id) throws ServiceException {
        return permissionDao.get(id);
    }

    
    public <K, V> Map<K, V> findOne(String id) throws ServiceException {
        return permissionDao.findOne(id);
    }

    
    public <T, K, V> List<T> find(Map<K, V> params) throws ServiceException {
        return permissionDao.find(params);
    }

    
    public int insert(Permission permission) throws ServiceException {
        permission.setId(StringUtil.createUUID());
        return permissionDao.insert(permission);
    }

    
    public int update(Permission permission) throws ServiceException {
        return permissionDao.update(permission);
    }

    
    public int delete(String id) throws ServiceException {
        return permissionDao.delete(id);
    }

    
    public PageList<PermissionResult> page(PermissionParam param) throws ServiceException {
        PageBounds pager = new PageBounds();
        pager.setLimit(param.getPageSize());
        pager.setPage(param.getPageNow() + 1);
        pager.setOrders(Order.formString(param.getSortString()));   
        return permissionDao.page(param,pager);
    }

}
