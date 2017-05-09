package com.csjbot.robot.admin.controller.sys;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.csjbot.robot.base.exception.ServiceException;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.ums.model.User;
import com.csjbot.robot.biz.ums.model.param.UserAdminSearchParam;
import com.csjbot.robot.biz.ums.model.result.UserResult;
import com.csjbot.robot.biz.ums.service.UserService;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 
 * @author sean
 */
@Controller
@RequestMapping(value="/sys/admin")
public class SysAdminController {
	
	@Autowired
	private UserService userService;

	/**
	 * 管理员列表页�?
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(){
		ModelAndView mav = new ModelAndView("/sys/sys_admin_list");
		return mav;
	}
	
	/**
	 * 新增系统管理员页�?
	 * @return
	 */
	@RequestMapping(value="/toSysAdminAdd")
	public ModelAndView toSysAdminAdd(){
		ModelAndView mav = new ModelAndView("/sys/sys_admin_add");
		return mav;
	}
	
	/**
	 * 新增系统管理�?
	 * @param user
	 * @param builder
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sysAdminAdd", method = RequestMethod.POST)
    public ResponseEntity<ResultEntity> sysAdminAdd(@RequestBody User user, UriComponentsBuilder builder, HttpServletRequest request) {
        ResultEntity result = null;
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        try {
            if (loginUser != null) {
                user.setCreator_fk(loginUser.getId());
                user.setUpdater_fk(loginUser.getId());
            }
            boolean status = userService.save(user);
            if (status) {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "新增系统管理员成�?");
            } else {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "新增系统管理员失�?");
            }

        } catch (ServiceException e) {
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/sys/admin/sysAdminAdd").buildAndExpand().toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
	
	/**
	 * 编辑页面
	 * @return
	 */
	@RequestMapping(value="/{id}/toSysAdminEdit")
	public ModelAndView toSysAdminEdit(@PathVariable String id){
		ModelAndView mav = new ModelAndView("/sys/sys_admin_edit");
		User user = userService.findById(id);
		mav.addObject(user);
		return mav;
	}
	
	/**
	 * 更新系统管理�?
	 * @param user
	 * @param builder
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sysAdminUpdate", method = RequestMethod.POST)
    public ResponseEntity<ResultEntity> sysAdminUpdate(@RequestBody User user, UriComponentsBuilder builder, HttpServletRequest request) {
        ResultEntity result = null;
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        try {
    	  if (loginUser != null) {
                user.setUpdater_fk(loginUser.getId());
          }
          if(!userService.checkPhoneExist(user.getPhone(), user.getId())){
            boolean status = userService.updateFinancialPlanner(user);
            if (status) {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "编辑系统管理员成�?");
            } else {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "编辑系统管理员失�?");
            }
          }else{
        	  result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "手机号码已存�?");
          }

        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/sys/admin/sysAdminUpdate").buildAndExpand().toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
	
	/**
	 * 详情页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/toSysAdminDetail")
	public ModelAndView toSysAdminDetail(@PathVariable String id){
		ModelAndView mav = new ModelAndView("/sys/sys_admin_detail");
		User user = userService.findById(id);
		userService.findRoles(user.getUsername());
		Set<String> set = userService.findRolesName(user.getUsername());
		String roles = "";
		for(String s:set) {
			roles+=s+" ,";
		}
		if(!roles.equals("")){
			roles = roles.substring(0,roles.length()-1);
		}
		mav.addObject("user",user);
		mav.addObject("roles",roles);
		return mav;
	}
	
	/**
	 * 修改个人密码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toChangePersonalPassword", method = RequestMethod.GET)
	public String toChangePersonalPassword(HttpServletRequest request, Model model){
	    
	    User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
	    model.addAttribute("user", loginUser);
	    return "index/personal_password";
	}
	
	/**
	 * 改密页面
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/{id}/toSysAdminPasswordChange", method = RequestMethod.GET)
    public ModelAndView sysAdminPasswordChange(@PathVariable String id) throws ServiceException {
        // --------------------------------------------------------------------------------------------
        User user = userService.findById(id);
        if (user == null) throw new ServiceException("Failed to get userResult by user id");
        ModelAndView mav = new ModelAndView("/sys/sys_admin_password");
        mav.addObject("user", user);
        // --------------------------------------------------------------------------------------------
        return mav;
    }
	
	/**
	 * 修改密码
	 * @param user
	 * @param request
	 * @param builder
	 * @return
	 */
	@RequestMapping(value = "/passwordChange", method = RequestMethod.POST)
    public ResponseEntity<ResultEntity> passwordChange(@RequestBody User user,HttpServletRequest request, UriComponentsBuilder builder) {
        ResultEntity result = null;
        try {
            User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
            if (loginUser != null) {
                boolean status = userService.updatePassword(user.getId(), user.getPassword(), loginUser.getId());
                if (status) {
                    result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "修改密码成功");
                } else {
                    result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "修改密码失败");
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/sys/admin/passwordChange").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
	
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ResponseEntity<ResultEntity> page(@RequestBody UserAdminSearchParam param, UriComponentsBuilder builder, HttpServletRequest request) {
        ResultEntity result = null;
        try {
        	PageList<UserResult> list = new PageList<UserResult>();
        	list = userService.grid(param);
            if (list != null) {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "search success");
                result.addObject("list", list);
                result.addObject("totalSize", list.size());
            } else {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "search fail!");
            }

        } catch (Exception e) {
        	e.printStackTrace();
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "数据异常，请重试");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/sys/admin/page").buildAndExpand().toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
}
