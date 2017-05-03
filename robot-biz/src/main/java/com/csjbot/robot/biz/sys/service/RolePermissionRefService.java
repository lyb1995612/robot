package com.csjbot.robot.biz.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.biz.sys.dao.RolePermissionRefDao;
import com.csjbot.robot.biz.sys.model.RolePermissionRef;

@Service
public class RolePermissionRefService {

    @Autowired
    private RolePermissionRefDao dao;

    
    public void saveOrUpdate(RolePermissionRef ref) {
        if (dao.update(ref) == 0) {
            if (StringUtil.isEmpty(ref.getId())) {
                ref.setId(StringUtil.createUUID());
            }
            dao.insert(ref);
        }
    }

    
    public int removeByRoleId(String roleId) {
        return dao.removeByRoleId(roleId);
    }

    
    public <T, K, V> List<T> getRolePermissionRef(Map<String, Object> params) {
        return dao.getRolePermissionRef(params);
    }

    
    public RolePermissionRef get(String id) {
        return dao.get(id);
    }

}
