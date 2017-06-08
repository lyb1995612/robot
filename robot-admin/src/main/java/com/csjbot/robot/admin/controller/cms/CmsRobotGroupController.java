package com.csjbot.robot.admin.controller.cms;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.csjbot.robot.base.page.Page;
import com.csjbot.robot.base.util.RequestUtil;
import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.cms.model.CmsRobotGroup;
import com.csjbot.robot.biz.cms.model.CmsRobotGroupRef;
import com.csjbot.robot.biz.cms.model.RobotGroupParam;
import com.csjbot.robot.biz.cms.service.CmsRobotGroupRefService;
import com.csjbot.robot.biz.cms.service.CmsRobotGroupService;
import com.csjbot.robot.biz.sys.model.SysDataDictionary;
import com.csjbot.robot.biz.sys.service.DictionaryService;
import com.csjbot.robot.biz.ums.model.User;

@Controller
@RequestMapping("rbg")
public class CmsRobotGroupController {
	
//	private Logger logger = Logger.getLogger(AdvertisementController.class);
	
	@Autowired
	private CmsRobotGroupService cmsRobotGroupService;
	
	@Autowired
	private CmsRobotGroupRefService cmsRobotGroupRefService;
	
	@Autowired 
	private DictionaryService dictionaryService;
	
	/**
	 * 显示机器人群组列表
	 * @return
	 */
	@RequestMapping(value = "/list")
    public ModelAndView portal() {
        ModelAndView mv = new ModelAndView("cms/robot_group_list");
        List<SysDataDictionary> cplist = cmsRobotGroupService.findDictionaryByCode(Constants.DataDictionary.CPFL);
		mv.addObject("cplist", cplist);
        return mv;
    }
	
	/**
	 * 跳转群组新增页面
	 * @return
	 */
	@RequestMapping(value = "/toRobotGroupAdd")
    public ModelAndView toRobotAdd() {
        ModelAndView mv = new ModelAndView("cms/robot_group_add");
        List<SysDataDictionary> cplist = cmsRobotGroupService.findDictionaryByCode(Constants.DataDictionary.CPFL);
		mv.addObject("cplist", cplist);
        return mv;
    }
	
	
	/**
	 * 新增机器人群组
	 * @param cmsRobotGroup
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> robotGroupAdd(CmsRobotGroup cmsRobotGroup ,HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
		String id = StringUtil.createUUID();
		cmsRobotGroup.setId(id);
		cmsRobotGroup.setCreator_fk(loginUser.getId());
		cmsRobotGroup.setUpdater_fk(loginUser.getId());
		String msg = "";
		if(cmsRobotGroupService.selectByGroupName(cmsRobotGroup.getGroup_name()) == null){
			if(cmsRobotGroupService.insert(cmsRobotGroup) > 0){
	        	msg = ResultEntity.KW_STATUS_SUCCESS;
	        }
		}else{
			msg = ResultEntity.KW_STATUS_FAIL;
		}
		
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
	
	/**
	 * 查询机器人群组列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<ResultEntity> page(HttpServletRequest request, HttpServletResponse response) {
        ResultEntity result = null;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            int length = Integer.valueOf(request.getParameter("length"));
            int start = Integer.valueOf(request.getParameter("start"));
            String orderColIndex = request.getParameter("order[0][column]");
            String dir = request.getParameter("order[0][dir]");
            String orderName = request.getParameter("columns[" + orderColIndex + "][data]");
            String group_name = request.getParameter("group_name");
            if (StringUtil.notEmpty(group_name)) {
               params.put("group_name", group_name);
            }
            String sortString = null;
            if (orderName != null && !"".equals(orderName) && dir != null && !"".equals(dir)) {
                sortString = orderName + "." + dir;
            }
            
            Page<Map<String, Object>> pageMap = cmsRobotGroupService.page(params, (start / length) + 1, length, sortString);
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "search success");
            if (pageMap.getRows() != null) {
                result.addObject("data", pageMap.getRows());
                result.addObject("recordsFiltered", pageMap.getTotal());
                result.addObject("recordsTotal", pageMap.getTotal());
            
            /*PageList<CmsRobotGroup> list = cmsRobotGroupService.page(params, (start / length) + 1, length, sortString);
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "search success");
            if (list != null && list.size() > 0) {
                result.addObject("data", list);
                result.addObject("recordsFiltered", list.size());
                result.addObject("recordsTotal", list.size());*/
            } else {
                result.addObject("data", null);
                result.addObject("recordsFiltered", 0);
                result.addObject("recordsTotal", 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }	
	
	/**
	 * 跳转到机器人关联页面
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/{id}/toRobotGroupRef")
    public ModelAndView assignPermission(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        CmsRobotGroup cmsRobotGroup = null;
		ModelAndView mv = new ModelAndView("cms/robot_group_ref");
        if (StringUtil.notEmpty(id)) {
        	cmsRobotGroup = cmsRobotGroupService.selectByPrimaryKey(id);
            if (cmsRobotGroup == null) {
                response.sendError(HttpStatus.NOT_FOUND.value());
                return null;
            }
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value());
            return null;
        }
        List<SysDataDictionary> cplist = cmsRobotGroupService.findDictionaryByCode(Constants.DataDictionary.CPFL);
		mv.addObject("cplist", cplist);
        mv.addObject("robotGroup", cmsRobotGroup);
        int relevance_num = cmsRobotGroupRefService.countRobotGroupSize(cmsRobotGroup.getId());
        mv.addObject("relevance_num", relevance_num);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("robotGroupId", id);
        List<Map<String, Object>> robot = cmsRobotGroupService.listRobot(params);
        mv.addObject("robot", robot);
        return mv;
    }
	
	/**
	 * 按条件检索机器人清单
	 * @param id
	 * @param param
	 * @param request
	 * @param response
	 * @param builder
	 * @return
	 */
	@RequestMapping(value = "/{id}/robotGroupRef/search")
    public ResponseEntity<ResultEntity> searchUser(@PathVariable String id, @RequestBody RobotGroupParam param, HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder builder)  {
        ResultEntity result = null;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("robotGroupId", id);
            params.put("sn", param.getSn());
            if(Integer.parseInt(param.getType_name()) != -1){
            	SysDataDictionary sysDataDictionary = dictionaryService.findSysDataDicById(Integer.parseInt(param.getType_name()));
                params.put("type_name", sysDataDictionary.getName());
            }
            List<Map<String, Object>> robot = cmsRobotGroupService.listRobot(params);
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "Success!", robot);

        } catch (Exception e) {
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/rbg/{id}/robotGroupRef/search").buildAndExpand(id).toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
	
    
	/**
	 * 关联机器人
	 * @param id
	 * @param request
	 * @param response
	 * @param builder
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/{id}/robotGroupRef/save")
    public ResponseEntity<ResultEntity> saveRolePermission(@PathVariable String id, HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder builder) throws IOException {
        ResultEntity result = null;
        User currentUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        try {
        	CmsRobotGroup cmsRobotGroup = cmsRobotGroupService.selectByPrimaryKey(id);
            if (cmsRobotGroup == null) {
                response.sendError(HttpStatus.NOT_FOUND.value());
            }
            CmsRobotGroupRef ref = null;
            int totalSize = Integer.parseInt(request.getParameter("robotGroupRef-totalSize"));
            for (int i = 0; i < totalSize; i++) {
                ref = (CmsRobotGroupRef) RequestUtil.fetchParameter(CmsRobotGroupRef.class, i, request);
                ref.setGroup_fk(id);
                ref.setCreator_fk(currentUser.getId());
                ref.setUpdater_fk(currentUser.getId());
                if (ref.isChecked() == false) {
                	CmsRobotGroupRef cmsRobotGroupRef = cmsRobotGroupRefService.get(ref.getId());
                    if(cmsRobotGroupRef != null){
                        ref.setValid(false);
                    }else{
                        continue;
                    }
                }
                cmsRobotGroupRefService.saveOrUpdate(ref);
            }
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "Success!");

        } catch (Exception e) {
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/rbg/{id}/robotGroupRef/save").buildAndExpand(id).toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
}
