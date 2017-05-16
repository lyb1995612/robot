package com.csjbot.robot.admin.controller.cms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csjbot.robot.admin.controller.pms.AdvertisementController;
import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.cms.model.CmsRobot;
import com.csjbot.robot.biz.cms.service.CmsRobotService;
import com.csjbot.robot.biz.sys.model.SysAttachment;
import com.csjbot.robot.biz.sys.model.SysDataDictionary;
import com.csjbot.robot.biz.ums.model.User;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping("crb")
public class CmsRobotController {
	
	private Logger logger = Logger.getLogger(AdvertisementController.class);
	
	@Autowired
	private CmsRobotService cmsRobotService;
	
/*	@Autowired
	private SysAttachService attachService;*/
	
	/**
	 * 机器人清单列表
	 * @return
	 */
	@RequestMapping(value = "/list")
    public ModelAndView portal() {
        ModelAndView mv = new ModelAndView("cms/robot_list");
        List<SysDataDictionary> cplist = cmsRobotService.findDictionaryByCode(Constants.DataDictionary.CPFL);
		mv.addObject("cplist", cplist);
        return mv;
    }
	
	/**
	 * 跳转到新增页面
	 * @return
	 */
	@RequestMapping(value = "/toRobotAdd")
    public ModelAndView toRobotAdd() {
        ModelAndView mv = new ModelAndView("cms/robot_add");
        List<SysDataDictionary> cplist = cmsRobotService.findDictionaryByCode(Constants.DataDictionary.CPFL);
		mv.addObject("cplist", cplist);
        return mv;
    }
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "{id}/toRobotUpdate")
	public ModelAndView toRobotUpdate(@PathVariable String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("cms/robot_update");
		mv = vrcEvent(mv, id, request);
		mv.addObject("editable", 1);
		return mv;
	}
	
	/**
	 * 跳转到详情页面
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "{id}/toRobotDetail")
	public ModelAndView toRobotDetail(@PathVariable String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("cms/robot_update");
		mv = vrcEvent(mv, id, request);
		mv.addObject("editable", 0);
		return mv;
	}
	
	public ModelAndView vrcEvent(ModelAndView mv, String id, HttpServletRequest request) {
		CmsRobot cmsRobot  = cmsRobotService.selectByPrimaryKey(id);
		List<SysDataDictionary> cplist = cmsRobotService.findDictionaryByCode(Constants.DataDictionary.CPFL);
		mv.addObject("cplist", cplist);
		mv.addObject("robot", cmsRobot);
		return mv;
	}
	
	/**
	 * 新增机器人清单
	 * @param cmsRobot
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> robotAdd(CmsRobot cmsRobot ,HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
		String id = StringUtil.createUUID();
		cmsRobot.setId(id);
		cmsRobot.setCreator_fk(loginUser.getId());
		cmsRobot.setUpdater_fk(loginUser.getId());
		String msg = "";
		SysAttachment attach = new SysAttachment();
		attach.setTransaction_id(id);
		attach.setTransaction_type(Constants.Attachment.Type.VERSION_ROBOT_FILE);
		attach.setOwner_fk(loginUser.getId());
		attach.setCreator_fk(loginUser.getId());
		attach.setUpdater_fk(loginUser.getId());
		attach.setSort(0);
		CmsRobot params = cmsRobotService.selectByTypeAndSn(cmsRobot.getType(), cmsRobot.getSn());
		if(params == null){
			if(cmsRobotService.insert(cmsRobot) > 0){
	        	msg = ResultEntity.KW_STATUS_SUCCESS;
	        }
		}else{
			    msg = ResultEntity.KW_STATUS_FAIL;
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
	
	/**
	 * 删除机器人清单
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "{id}/robotDelete")
	public ResponseEntity<String> versionDelete(@PathVariable String id, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		String msg = ResultEntity.KW_STATUS_SUCCESS;
		try {
			cmsRobotService.delete(id);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
	
	
	/**
	 * 机器人清单更新
	 * @param pmsProduct
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> productUpdate(CmsRobot cmsRobot,HttpServletRequest request,HttpServletResponse response){
	    JSONObject result = new JSONObject();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    response.setCharacterEncoding("UTF-8");
	    User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
	    cmsRobot.setUpdater_fk(loginUser.getId());
	    String msg = "";
        SysAttachment attach = new SysAttachment();
        attach.setTransaction_id(cmsRobot.getId());
        attach.setTransaction_type(Constants.Attachment.Type.VERSION_ROBOT_FILE);
        attach.setUpdater_fk(loginUser.getId());
	    if(cmsRobotService.updateByPrimaryKey(cmsRobot) > 0){
	    	msg = ResultEntity.KW_STATUS_SUCCESS;
	    }
	    result.put("msg", msg);
	    return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
	
	
	/**
	 * 机器人列表显示
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
            String sn = request.getParameter("sn");
            String type = request.getParameter("type");
            params.put("sn", sn);
            params.put("type", type);
            String sortString = null;
            if (orderName != null && !"".equals(orderName) && dir != null && !"".equals(dir)) {
                sortString = orderName + "." + dir;
            }
            PageList<CmsRobot> list = cmsRobotService.page(params, (start / length) + 1, length, sortString);
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "search success");
            if (list != null && list.size() > 0) {
                result.addObject("data", list);
                result.addObject("recordsFiltered", list.size());
                result.addObject("recordsTotal", list.size());
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

}
