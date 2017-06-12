package com.csjbot.robot.biz.sms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.csjbot.robot.biz.sms.dao.Sys_attachmentDAO;
import com.csjbot.robot.biz.sms.dao.Sys_dataDAO;
import com.csjbot.robot.biz.sms.dao.Sys_data_dictionaryDAO;
import com.csjbot.robot.biz.sms.dao.Sys_version_robotDAO;
import com.csjbot.robot.biz.sms.model.Sys_attachment;
import com.csjbot.robot.biz.sms.model.Sys_data;
import com.csjbot.robot.biz.sms.model.Sys_data_dictionary;
import com.csjbot.robot.biz.sms.model.Sys_version_robot;
import com.csjbot.robot.biz.util.FileZipUtil;
import com.csjbot.robot.biz.util.JsonUtil;

/**          
     * Description 
     * @author CJay       
     * @created 2017年6月12日 上午11:55:01    
     */
@Service 
public class VersionRobotServiceImpl implements VersionRobotService {

	  
	    /** 
	     * @author CJay       
	     * @created 2017年6月12日 上午11:56:24        
	     */  
	
	@Autowired
	private Sys_version_robotDAO sys_version_robotDAO;
	  

	@Autowired
    private Sys_attachmentDAO sys_attachmentDAO;
	
	@Autowired
	private Sys_dataDAO sys_dataDAO;
	
	@Autowired
	private Sys_data_dictionaryDAO sys_data_dictionaryDAO;
	

	@Override
	public JSONObject returnRobotVersion(HttpServletRequest request) {

		JsonUtil jsonUtil = getJsonUtilEntity(true);
		boolean upgrade = false;
		String category =  request.getParameter("category");
		String channel = request.getParameter("channel");
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> content = new HashMap<String, Object>();
		Sys_data sys_data1 = sys_dataDAO.findCodeById(FileZipUtil.CategoryCode);
		Sys_data sys_data2 = sys_dataDAO.findCodeById(FileZipUtil.ChannelCode);
 		Sys_data_dictionary params1= sys_data_dictionaryDAO.findDataDictionaryById(sys_data1.getId(),category);
		Sys_data_dictionary params2= sys_data_dictionaryDAO.findDataDictionaryById(sys_data2.getId(),channel);
		if(params1 != null ){
			if(params1.getRule() != 0){
			   upgrade = true;
			   Sys_version_robot params3 = new Sys_version_robot();
			   List<Sys_version_robot> list = sys_version_robotDAO.findSysVersionBycach(params1.getId(),params2.getId());
			   if( list.size() != 0){
				   for(Sys_version_robot svr : list){
					   if(svr.getVersion_code() != 0){
						   //判断版本号中最大的那个
							   params3 = sys_version_robotDAO.findSysByVersionCode(params1.getId(),params2.getId());
					   }else{
						   //判断更新日期最早的那个
						      params3 = sys_version_robotDAO.findSysByDateUpdate(params1.getId(),params2.getId());
					   }
				   }
				   content.put("category", category);
				   content.put("channel", channel);
				   content.put("version_code", params3.getVersion_code());
				   content.put("version_name", params3.getVersion_name());
				   content.put("checksum", params3.getMd5());
				   List<Sys_attachment> saList = sys_attachmentDAO.getSystByProId(params3.getId().toString());
				   content.put("url",request.getServerName()+":8001/tms/"+saList.get(0).getAlias_name().toString());
				   
			   }else{
				   jsonUtil = getJsonUtilEntity(false);
				   jsonUtil.setMessage("升级版本为空!");
			   }
			}else{
				jsonUtil = getJsonUtilEntity(false);
				jsonUtil.setMessage("版本不可升级!");
			}
			content.put("upgrade", upgrade);
			result.put("resule", content);
			jsonUtil.setResult(result);
		} else {
			jsonUtil = getJsonUtilEntity(false);
			jsonUtil.setMessage("Error from json format!");
		}
		
		return JsonUtil.toJson(jsonUtil);
	
	}

	
	//
	public JsonUtil getJsonUtilEntity(boolean flag) {
		JsonUtil jsonUtil;
		if (flag) {
			jsonUtil = new JsonUtil("200", "ok", null);
		} else {
			jsonUtil = new JsonUtil("500", "", null);
		}
		return jsonUtil;
	}
}
