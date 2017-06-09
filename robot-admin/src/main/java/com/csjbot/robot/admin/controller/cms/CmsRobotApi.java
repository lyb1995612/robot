package com.csjbot.robot.admin.controller.cms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.biz.cms.model.CmsRobot;
import com.csjbot.robot.biz.cms.service.CmsRobotService;

/**
 * 
 * @Title
 * @Description 机器人对应安卓的API
 * @date 2017年5月19日 下午4:18:40
 * @author SJZ
 */
@Controller
@RequestMapping("cmsRobotApi")
public class CmsRobotApi {
	@Autowired
	private CmsRobotService cmsRobotService;

	/**
	 * 新增机器人清单通过安卓程序
	 * 
	 * @param cmsRobot
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addOfAz", method = RequestMethod.POST)
	public ResponseEntity<String> robotAddOfAz(CmsRobot cmsRobot, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		String id = StringUtil.createUUID();
		cmsRobot.setId(id);
		cmsRobot.setCreator_fk("superrobot");
		cmsRobot.setUpdater_fk("superrobot");
		String msg = "";
		CmsRobot params = cmsRobotService.selectBySN(cmsRobot.getSn());
		if (params == null) {
			if (cmsRobotService.insert(cmsRobot) > 0) {
				msg = ResultEntity.KW_STATUS_SUCCESS;
			}
		} else {
			msg = ResultEntity.KW_STATUS_FAIL;
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

}
