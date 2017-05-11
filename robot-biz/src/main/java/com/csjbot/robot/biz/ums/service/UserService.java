package com.csjbot.robot.biz.ums.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.base.exception.ServiceException;
import com.csjbot.robot.biz.base.util.StringUtil;
import com.csjbot.robot.biz.ums.dao.UserDao;
import com.csjbot.robot.biz.ums.model.User;
import com.csjbot.robot.biz.ums.model.param.UserAdminSearchParam;
import com.csjbot.robot.biz.ums.model.result.UserResult;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public PageList<UserResult> grid(UserAdminSearchParam param) {
        PageBounds pager = new PageBounds();
        pager.setLimit(param.getPageSize());
        pager.setPage(param.getPageNow() + 1);
        pager.setOrders(Order.formString(param.getSortString()));
        return userDao.page(param, pager);
    }

    public boolean save(User user) throws ServiceException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", user.getUsername());
        params.put("phone", user.getPhone());
        boolean b = userDao.checkUserExist(params)>0;
        String msg = "";
        if (b) {
            if (StringUtil.notEmpty(user.getUsername()) && StringUtil.notEmpty(user.getPhone())) {
                msg = "账号为 [" + user.getUsername() + "] 的用户或 [" + user.getPhone() + "]手机号已经存在";
            } else if (StringUtil.notEmpty(user.getUsername())) {
                msg = "账号为 [" + user.getUsername() + "] 的用户已经存在";
            } else {
                msg = "手机号为[" + user.getPhone() + "] 的用户已经存在";
            }
            throw new ServiceException(msg);
        }
        user.setId(StringUtil.createUUID());
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(user);
        return userDao.insert(user) > 0;
    }

    
    public boolean saveFinancialPlanner(User user) throws ServiceException{
        user.setId(StringUtil.createUUID());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", user.getUsername());
        params.put("phone", user.getPhone());
        boolean b = userDao.checkUserExist(params)>0;
        String msg = "";
        if (b) {
            if (StringUtil.notEmpty(user.getUsername()) && StringUtil.notEmpty(user.getPhone())) {
                msg = "账号为 [" + user.getUsername() + "] 的用户或 [" + user.getPhone() + "]手机号已经存在";
            } else if (StringUtil.notEmpty(user.getUsername())) {
                msg = "账号为 [" + user.getUsername() + "] 的用户已经存在";
            } else {
                msg = "手机号为[" + user.getPhone() + "] 的用户已经存在";
            }
            throw new ServiceException(msg);
        }
        return userDao.insert(user) > 0;
    }

    
    public boolean updateFinancialPlanner(User user) {
        return userDao.updateFinancialPlanner(user);
    }

    
    public boolean update(User tbUser) {
        return userDao.update(tbUser) > 0;
    }

    
    public User findById(String id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return userDao.get(params);
    }

    
    public boolean deleteById(String id) {
        return userDao.delete(id) > 0;
    }

    
    public boolean updatePassword(String id, String password, String updater_fk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        User user = userDao.get(params);
        if (user != null && StringUtil.notEmpty(password)) {
            user.setUpdater_fk(updater_fk);
            user.setPassword(password);
            user.setPasswordChanged(1);
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user);
            return userDao.update(user) > 0;
        }
        return false;
    }

    
    public User getByUsername(String username, int status) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("status", status);
        User user = userDao.getByUsername(params);
        return user;
    }

    public Set<String> findRoles(String account) {
        Set<String> roles = new HashSet<String>();
        roles.addAll((List<String>) userDao.listRolesByUsername(account));
        // --------------------------------------------------------------------------------------------------
        return roles;
    }

    
    public Set<String> findPermissions(String account) {
        Set<String> permissions = new HashSet<String>();
        permissions.addAll((List<String>) userDao.listPermissionsByUsername(account));
        // --------------------------------------------------------------------------------------------------
        return permissions;
    }

    
    public Map<String, Object> findOne(String id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return userDao.findOne(params);

    }

    
    public List<Map<String, Object>> listFinancialByArea(String area_fk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("area_fk", area_fk);
        return userDao.listFinancialByArea(params);
    }

    
    public boolean userDistribution(User registeredUser, User financialUser) {
        int count = userDao.update(registeredUser);
        count = count + userDao.update(financialUser);
        return count > 1;
    }

	
	public boolean updateComment(User user) {
		return userDao.updateComment(user)>0;
	}
	
    
    public boolean checkPhoneExist(String phone, String uId) {
    	Map<String, Object> params = new HashMap<String, Object>();
        params.put("phone", phone);
        params.put("id", uId);
        User user = userDao.getByPhone(params);
        if (user == null || user.getId().equals(uId)) {
            return false;
        }
        return true;
    }

	
	public Set<String> findRolesName(String username) {
		   Set<String> roles = new HashSet<String>();
	        roles.addAll((List<String>) userDao.findRolesName(username));
		return roles;
	}
}
