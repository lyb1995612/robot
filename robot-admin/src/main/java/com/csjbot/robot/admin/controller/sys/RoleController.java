package com.csjbot.robot.admin.controller.sys;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.csjbot.robot.biz.base.util.RequestUtil;
import com.csjbot.robot.biz.base.util.StringUtil;
import com.csjbot.robot.biz.base.entity.ResultEntity;
import com.csjbot.robot.biz.base.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.sys.model.Role;
import com.csjbot.robot.biz.sys.model.RolePermissionRef;
import com.csjbot.robot.biz.sys.model.UserRoleRef;
import com.csjbot.robot.biz.sys.model.param.RoleParam;
import com.csjbot.robot.biz.sys.model.param.UserRoleParam;
import com.csjbot.robot.biz.sys.service.RolePermissionRefService;
import com.csjbot.robot.biz.sys.service.RoleService;
import com.csjbot.robot.biz.sys.service.UserRoleRefService;
import com.csjbot.robot.biz.ums.model.User;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
    
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleRefService refService;
    @Autowired
    private RolePermissionRefService permissionRefService;
    
    /**
     * 列出�?有的角色
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView portal() {
        ModelAndView mv = new ModelAndView("sys/role_list");
        return mv;
    }
    
    /**
     * 获取�?有的角色的数�?
     * @param param
     * @param request
     * @param builder
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<ResultEntity> search(@RequestBody RoleParam param, HttpServletRequest request, UriComponentsBuilder builder) {
        ResultEntity result = null;
        try {
            PageList<Role> list = new PageList<Role>();
            list = roleService.page(param);
            if (list != null) {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "success");
                result.addObject("list", list);
                result.addObject("totalSize", list.size());
            } else {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "search fail!");
            }

        } catch (Exception e) {
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
            e.printStackTrace();
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/role/search").buildAndExpand().toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
    
    /**
     * 新建角色
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/create")
    public ModelAndView create(HttpServletRequest request) throws IOException {
        ModelAndView mv = new ModelAndView("sys/role");
        Role role = new Role();
        mv.addObject("action", "CREATE");
        mv.addObject("role", role);
        return mv;
    }
    
    /**
     * 保存角色
     * @param role
     * @param request
     * @param builder
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ResultEntity> save(@RequestBody Role role, HttpServletRequest request, UriComponentsBuilder builder) {
        ResultEntity result = null;
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        role.setCreator_fk(loginUser.getId());
        role.setUpdater_fk(loginUser.getId());
        role.setDateOfCreate(new Date());
        role.setDateOfUpdate(new Date());
        
        roleService.saveOrUpdate(role);
        result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "success");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/role/save").buildAndExpand().toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
    
    /**
     * 角色详情
     * @param id
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}")
    public ModelAndView form(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role role = null;
        ModelAndView mv = new ModelAndView("sys/role");
        if (StringUtil.notEmpty(id)) {
            role = roleService.get(id);
            if (role == null) {
                response.sendError(HttpStatus.NOT_FOUND.value());
                return null;
            }
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value());
            return null;
        }
        mv.addObject("role", role);
        return mv;
    }
    
    /**
     * 编辑角色
     * @param id
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}/edit")
    public ModelAndView edit(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role role = null;
        ModelAndView mv = new ModelAndView("sys/role");
        if (StringUtil.notEmpty(id)) {
            role = roleService.get(id);
            if (role == null) {
                response.sendError(HttpStatus.NOT_FOUND.value());
                return null;
            }
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value());
            return null;
        }
        mv.addObject("role", role);
        mv.addObject("action", "EDIT");
        return mv;
    }
    
    /**
     * 角色  关联用户
     * @param id
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}/assign-user")
    public ModelAndView assignUser(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role role = null;
        ModelAndView mv = new ModelAndView("sys/assign-user");
        if (StringUtil.notEmpty(id)) {
            role = roleService.get(id);
            if (role == null) {
                response.sendError(HttpStatus.NOT_FOUND.value());
                return null;
            }
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value());
            return null;
        }
        mv.addObject("role", role);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", role.getId());
        List<Map<String, Object>> admins = refService.listAdmins(params);
        mv.addObject("admins", admins);
        int userSize = refService.countRoleUserSize(role.getId());
        mv.addObject("userSize", userSize);
        return mv;
    }
    
    /**
     * 按条件搜索用�?
     * @param id
     * @param param
     * @param request
     * @param response
     * @param builder
     * @return
     */
    @RequestMapping(value = "/{id}/user/search")
    public ResponseEntity<ResultEntity> searchUser(@PathVariable String id, @RequestBody UserRoleParam param, HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder builder)  {
        ResultEntity result = null;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("roleId", id);
            params.put("account", param.getAccount());
            params.put("username", param.getUsername());
            if (StringUtil.notEmpty(param.getStatus())) {
                params.put("status", UserRoleParam.STATUS_ENABLED.equals(param.getStatus()) ? "1" : "0");
            }
            List<Map<String, Object>> admins = refService.listAdmins(params);
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "Success!", admins);

        } catch (Exception e) {
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/role/{id}/user/search").buildAndExpand(id).toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
    
    /**
     * 保存 角色 用户 关系
     * @param id
     * @param request
     * @param response
     * @param builder
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}/user/save")
    public ResponseEntity<ResultEntity> saveRoleUser(@PathVariable String id, HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder builder) throws IOException {
        ResultEntity result = null;
        User currentUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        try {
            Role role = roleService.get(id);
            if (role == null) {
                response.sendError(HttpStatus.NOT_FOUND.value());
            }
            UserRoleRef ref = null;
            int totalSize = Integer.parseInt(request.getParameter("UserRoleRef-totalSize"));
            for (int i = 0; i < totalSize; i++) {
                ref = (UserRoleRef) RequestUtil.fetchParameter(UserRoleRef.class, i, request);
                ref.setRole_fk(id);
                ref.setCreator_fk(currentUser.getId());
                ref.setUpdater_fk(currentUser.getId());
                if (ref.isChecked() == false) {
                    UserRoleRef userRoleRef = refService.getUserRoleRefById(ref.getId());
                     if(userRoleRef!=null){
                         ref.setValid(false);
                     }else{
                         continue;
                     }
                }
                refService.saveOrUpdate(ref);
            }
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "Success!");

        } catch (Exception e) {
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/role/{id}/user/save").buildAndExpand(id).toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
    
    
    /**
     * 角色 关联权限
     * @param id
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}/assign-permission")
    public ModelAndView assignPermission(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role role = null;
        ModelAndView mv = new ModelAndView("sys/assign-permission");
        if (StringUtil.notEmpty(id)) {
            role = roleService.get(id);
            if (role == null) {
                response.sendError(HttpStatus.NOT_FOUND.value());
                return null;
            }
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value());
            return null;
        }
        mv.addObject("role", role);
        int userSize = refService.countRoleUserSize(role.getId());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", id);
        List<Map<String, Object>> permissions = permissionRefService.getRolePermissionRef(params);
        mv.addObject("permissions", permissions);
        mv.addObject("userSize", userSize);
        return mv;
    }
    
    /**
     * 保存 角色--权限 关系
     * @param id
     * @param params
     * @param request
     * @param response
     * @param builder
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}/permission/save")
    public ResponseEntity<ResultEntity> saveRolePermission(@PathVariable String id, HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder builder) throws IOException {
        ResultEntity result = null;
        User currentUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        try {
            Role role = roleService.get(id);
            if (role == null) {
                response.sendError(HttpStatus.NOT_FOUND.value());
            }
            RolePermissionRef ref = null;
            int totalSize = Integer.parseInt(request.getParameter("rolePerminssionRef-totalSize"));
            for (int i = 0; i < totalSize; i++) {
                ref = (RolePermissionRef) RequestUtil.fetchParameter(RolePermissionRef.class, i, request);
                ref.setRole_fk(id);
                ref.setCreator_fk(currentUser.getId());
                ref.setUpdater_fk(currentUser.getId());
                if (ref.isChecked() == false) {
                    RolePermissionRef userRoleRef = permissionRefService.get(ref.getId());
                    if(userRoleRef != null){
                        ref.setValid(false);
                    }else{
                        continue;
                    }
                }
                permissionRefService.saveOrUpdate(ref);
            }
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "Success!");

        } catch (Exception e) {
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/role/{id}/permission/save").buildAndExpand(id).toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
}
