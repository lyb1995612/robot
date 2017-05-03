package com.csjbot.robot.biz.ums.dao;

import java.util.List;
import java.util.Map;

import com.csjbot.robot.biz.ums.model.User;
import com.csjbot.robot.biz.ums.model.param.UserAdminSearchParam;
import com.csjbot.robot.biz.ums.model.result.UserResult;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 
 * @author Cjay
 *
 */
public interface UserDao {

    public User get(Map<String, Object> params);

    public Map<String, Object> findOne(Map<String, Object> params);

    public int insert(User user);

    public int update(User user);
    
    public int updateComment(User user);

    public int delete(String id);

    public PageList<UserResult> page(UserAdminSearchParam param, PageBounds pager);

    public User getByUsername(Map<String, Object> params);

    public User getByPhone(Map<String, Object> params);

    public Integer checkUserExist(Map<String, Object> params);

    public List<String> listRolesByUsername(String username);

    public List<String> listPermissionsByUsername(String username);

    public boolean updateFinancialPlanner(User user);

    public List<Map<String, Object>> listFinancialByArea(Map<String, Object> params);

//    public User getByRecommendedCode(String recommendedCode);

    public int realNameAuth(User user);

    public int changeYeepayStatus(Map<String, Object> params);

	public List<String> findRolesName(String username);
}
