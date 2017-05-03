package com.csjbot.robot.biz.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.sys.dao.RoleDao;
import com.csjbot.robot.biz.sys.model.Role;
import com.csjbot.robot.biz.sys.model.param.RoleParam;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


@Service
public class RoleService {

    @Autowired
    private RoleDao dao;

    
    public Role get(String id) {
        return dao.get(id);
    }

    
    public void saveOrUpdate(Role role) {
        if (dao.update(role) <= 0) {
            dao.insert(role);
        }
    }

    
    public List<Map<String, Object>> find(Map<String, Object> params) {
        return dao.find(params);
    }

    
    public PageList<Role> page(RoleParam param) {
        PageBounds pager = new PageBounds();
        pager.setLimit(param.getPageSize());
        pager.setPage(param.getPageNow() + 1);
        pager.setOrders(Order.formString(param.getSortString()));    	
        return dao.page(param,pager);
    }
}
