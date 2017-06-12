package com.csjbot.robot.api.sms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csjbot.robot.biz.sms.service.VersionRobotService;
import com.csjbot.robot.biz.util.ResponseUtil;

/**          
     * Description 机器人版本
     * @author CJay       
     * @created 2017年6月12日 上午11:47:56    
     */

@Controller
public class VersionRobotController {
	
	@Autowired
	public VersionRobotService versionRobotService;
	
	
	// 机器人升级版本数据
		@RequestMapping(value = "/tms/getVersionRobot", method = RequestMethod.GET)
		@ResponseBody
		public void versionRobot(HttpServletRequest request, HttpServletResponse response)
				throws IOException {
			ResponseUtil.write(response, versionRobotService.returnRobotVersion(request));
		}

}
