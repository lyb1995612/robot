package com.csjbot.robot.biz.sms.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**          
     * Description 
     * @author CJay       
     * @created 2017年6月12日 上午11:51:01    
     */

public interface VersionRobotService {
	
	//获取机器人升级版本数据
	 public abstract JSONObject returnRobotVersion(HttpServletRequest request);

	

}
