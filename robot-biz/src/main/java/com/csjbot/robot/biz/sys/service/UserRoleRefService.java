package com.csjbot.robot.biz.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.base.util.StringUtil;
import com.csjbot.robot.biz.sys.dao.UserRoleRefDao;
import com.csjbot.robot.biz.sys.model.UserRoleRef;

@Service
public class UserRoleRefService {

    @Autowired
    private UserRoleRefDao dao;

    
    public int countRoleUserSize(String roleId) {
        return dao.countRoleUserSize(roleId);
    }

    
    public List<Map<String, Object>> listAdmins(Map<String, Object> params) {
        return dao.listAdmins(params);
    }

    
    public void saveOrUpdate(UserRoleRef ref) {
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

	
	public UserRoleRef getUserRoleRefById(String id) {
		return dao.get(id);
	}

}
