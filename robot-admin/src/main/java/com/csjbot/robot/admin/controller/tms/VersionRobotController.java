package com.csjbot.robot.admin.controller.tms;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.csjbot.robot.base.util.MD5Util;
import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.sys.model.SysAttachment;
import com.csjbot.robot.biz.sys.model.SysDataDictionary;
import com.csjbot.robot.biz.sys.model.SysVersionRobot;
import com.csjbot.robot.biz.sys.service.DictionaryService;
import com.csjbot.robot.biz.sys.service.SysAttachService;

import com.csjbot.robot.biz.tms.service.VrcService;
import com.csjbot.robot.biz.ums.model.User;
import com.csjbot.robot.biz.util.ErrorRealm;
import com.csjbot.robot.biz.util.FileUtil;
import com.csjbot.robot.biz.util.ReadUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping(value = "/vrc")
public class VersionRobotController {

	private Logger logger = Logger.getLogger(VersionRobotController.class);

	@Autowired
	private VrcService vrcService;

	@Autowired
	private DictionaryService dicService;

	@Autowired
	private SysAttachService attachService;

	//文件路径
	private final String nginx_path = "/opt/pkg";

	/**
	 * @discription 版本列表
	 * @author CJay
	 * @created 2017�?4�?20�? 下午2:44:52
	 */
	@RequestMapping(value = "/list")
	public ModelAndView portal() {
		ModelAndView mv = new ModelAndView("tms/version_robot_list");
		List<SysDataDictionary> cplist = dicService.findDictionaryByCode(Constants.DataDictionary.CPFL);
		mv.addObject("cplist", cplist);
		List<SysDataDictionary> bblist = dicService.findDictionaryByCode(Constants.DataDictionary.BBLX);
		mv.addObject("bblist", bblist);
		return mv;
	}

	/**
	 * @discription 修改�?
	 * @author CJay
	 * @created 2017�?4�?21�? 上午10:19:59
	 */
	@RequestMapping(value = "{id}/toVersionUpdate")
	public ModelAndView toProducUpdate(@PathVariable String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("tms/version_robot_update");
		mv = vrcEvent(mv, id, request);
		mv.addObject("editable", 1);
		return mv;
	}

	/**
	 * @discription 详情�?
	 * @author CJay
	 * @created 2017�?4�?21�? 上午10:20:27
	 */
	@RequestMapping(value = "{id}/toVersionDetail")
	public ModelAndView toProductDetail(@PathVariable String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("tms/version_robot_update");
		mv = vrcEvent(mv, id, request);
		mv.addObject("editable", 0);
		return mv;
	}

	public ModelAndView vrcEvent(ModelAndView mv, String id, HttpServletRequest request) {
		SysVersionRobot sysVersionRobot = vrcService.selectByPrimaryKey(id);
		List<SysDataDictionary> cplist = dicService.findDictionaryByCode(Constants.DataDictionary.CPFL);
		mv.addObject("cplist", cplist);
		List<SysDataDictionary> bblist = dicService.findDictionaryByCode(Constants.DataDictionary.BBLX);
		mv.addObject("bblist", bblist);
		mv.addObject("sysVersionRobot", sysVersionRobot);
		SysAttachment attach = attachService.getAttachByTransInfo(sysVersionRobot.getId(),
				Constants.Attachment.Type.VERSION_ROBOT_FILE);
		//mv.addObject("file_url","/attach/"+sysVersionRobot.getId()+"/"+Constants.Attachment.Type.VERSION_ROBOT_FILE+"/"+attach.getName());
		//mv.addObject("file_url","/attach/"+sysVersionRobot.getId()+"/"+Constants.Attachment.Type.VERSION_ROBOT_FILE+"/tms";
		//mv.addObject("file_url","/attach/"+sysVersionRobot.getId()+"/"+Constants.Attachment.Type.VERSION_ROBOT_FILE+"/tms"); 
		mv.addObject("file_url", "http://" + request.getServerName() + ":8001"
				+ Constants.Attachment.Path.VERSION_ROBOT_FILE_PATH + attach.getAlias_name());
		mv.addObject("file_name", attach.getOriginal_name());
		return mv;
	}

	/**
	 * @discription 跳转新增页面
	 * @author CJay
	 * @created 2017�?4�?20�? 下午2:45:07
	 */
	@RequestMapping(value = "/toVersionAdd")
	public ModelAndView toProductAdd() {
		ModelAndView mv = new ModelAndView("tms/version_robot_add");
		List<SysDataDictionary> cplist = dicService.findDictionaryByCode(Constants.DataDictionary.CPFL);
		mv.addObject("cplist", cplist);
		List<SysDataDictionary> bblist = dicService.findDictionaryByCode(Constants.DataDictionary.BBLX);
		mv.addObject("bblist", bblist);
		return mv;
	}

	/**
	 * @discription 新增版本
	 * @author CJay
	 * @created 2017�?3�?23�? 上午11:03:26
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> productAdd(SysVersionRobot sysVersionRobot,
			@RequestParam(required = false) MultipartFile ver_file, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		// FileUtil fileUtil = new FileUtil();
		User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
		MD5Util md5 = new MD5Util();
		String id = StringUtil.createUUID();
		sysVersionRobot.setId(id);
		sysVersionRobot.setMd5(md5.genChecksum(ver_file));
		sysVersionRobot.setCreator_fk(loginUser.getId());
		sysVersionRobot.setUpdater_fk(loginUser.getId());
		String msg = "";
		String url = null;
		SysAttachment attach = new SysAttachment();
		attach.setTransaction_id(id);
		attach.setTransaction_type(Constants.Attachment.Type.VERSION_ROBOT_FILE);
		attach.setOwner_fk(loginUser.getId());
		attach.setCreator_fk(loginUser.getId());
		attach.setUpdater_fk(loginUser.getId());
		attach.setMemo(sysVersionRobot.getMemo());
		attach.setSort(0);
		FileUtil fileUtil = new FileUtil();
		String ver_file_url = null;
		if (ver_file != null) {
			attachService.deleteByTransInfo(attach.getTransaction_id(),attach.getTransaction_type());
	    	ver_file_url=fileUtil.uploadAndModifyAttach(attachService,attach, ver_file, nginx_path, Constants.Attachment.Path.VERSION_ROBOT_FILE_PATH);
			
			if ("error".equals(ver_file_url)) {
		    	msg = ResultEntity.KW_STATUS_FAIL;
		    	result.put("msg", msg);
		    	logger.error("Product upload picture error!");
		        return new ResponseEntity<String>(result.toString() ,headers, HttpStatus.OK);
		    }
		}
		/*if (ver_file != null) {
			ErrorRealm errorRealm = new ErrorRealm();
			url = errorRealm.uploadAndModifyAttach(attach, Constants.Attachment.Type.VERSION_ROBOT_FILE, ver_file,
					nginx_path, Constants.Attachment.Path.VERSION_ROBOT_FILE_PATH);
			if ("error".equals(url)) {
				msg = ResultEntity.KW_STATUS_FAIL;
				result.put("msg", msg);
				logger.error("Product upload picture error!");
				return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
			}
		}*/
		
		if (vrcService.insert(sysVersionRobot)) {
			msg = ResultEntity.KW_STATUS_SUCCESS;
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

	/**
	 * @discription 修改版本
	 * @author CJay
	 * @created 2017�?4�?21�? 下午4:52:52
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> versionUpdate(SysVersionRobot sysVersionRobot, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		FileUtil fileUtil = new FileUtil();
		String ver_file_url = null;
		User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
		sysVersionRobot.setUpdater_fk(loginUser.getId());
		String msg = "";
		String flag = null;
		SysAttachment attach = new SysAttachment();
		attach.setTransaction_id(sysVersionRobot.getId());
		attach.setUpdater_fk(loginUser.getId());
		if (request instanceof MultipartHttpServletRequest) {
			MultipartFile ver_file = ((MultipartHttpServletRequest) request).getFile("ver_file");
			
			if (ver_file != null) {
				attachService.deleteByTransInfo(attach.getTransaction_id(),attach.getTransaction_type());
		    	ver_file_url=fileUtil.uploadAndModifyAttach(attachService,attach, ver_file, nginx_path, Constants.Attachment.Path.VERSION_ROBOT_FILE_PATH);
				
				if ("error".equals(ver_file_url)) {
			    	msg = ResultEntity.KW_STATUS_FAIL;
			    	result.put("msg", msg);
			    	logger.error("Product upload picture error!");
			        return new ResponseEntity<String>(result.toString() ,headers, HttpStatus.OK);
			    }
			}
			
			
			/*if (ver_file != null) {
				attachService.deleteByTransInfo(attach.getTransaction_id(),
						Constants.Attachment.Type.VERSION_ROBOT_FILE);
				ErrorRealm errorRealm = new ErrorRealm();
				flag = errorRealm.uploadAndModifyAttach(attach, Constants.Attachment.Type.VERSION_ROBOT_FILE, ver_file,
						nginx_path, Constants.Attachment.Path.VERSION_ROBOT_FILE_PATH);
				if ("error".equals(flag)) {
					msg = ResultEntity.KW_STATUS_FAIL;
					result.put("msg", msg);
					logger.error("Version file upload error!");
					return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
				}
			}*/
		}
		if (vrcService.updateVersionRobot(sysVersionRobot)) {
			msg = ResultEntity.KW_STATUS_SUCCESS;
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

	/**
	 * @discription 版本删除
	 * @author CJay
	 * @created 2017�?4�?21�? 下午2:15:55
	 */
	@RequestMapping(value = "{id}/versionDelete")
	public ResponseEntity<String> versionDelete(@PathVariable String id, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		String msg = ResultEntity.KW_STATUS_SUCCESS;
		try {
			attachService.deleteByTransInfo(id.trim(), Constants.Attachment.Type.VERSION_ROBOT_FILE);
			vrcService.deleteVersionByPrimaryKey(id);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

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

            String version_name = request.getParameter("version_name");
            String category = request.getParameter("category");
            String channel = request.getParameter("channel");
            params.put("version_name", version_name);
            params.put("category", category);
            params.put("channel", channel);
            String sortString = null;
            if (orderName != null && !"".equals(orderName) && dir != null && !"".equals(dir)) {
                sortString = orderName + "." + dir;
            }
            PageList<SysVersionRobot> list = vrcService.versPage(params, (start / length) + 1, length, sortString);
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "search success");
            if (list != null) {
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
	
	@RequestMapping(value = "/getVersionCode", method = RequestMethod.POST)
	public ResponseEntity<String> getVersionCode(@RequestParam(value="file", required=false) MultipartFile file, HttpServletRequest request,	HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		File f = null;
	    f=File.createTempFile("tmp", null);
	    file.transferTo(f);
	    f.deleteOnExit();       
        Map<String,Object> res= ReadUtil.readVersionCode(f);
        String versionCode= res.get("versionCode").toString();
        String versionName= res.get("versionName").toString();
		System.out.println(versionCode);
		result.put("versionCode", versionCode);
		result.put("versionName", versionName);
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		// FileUtil fileUtil = new FileUtil();
		result.put("msg", ResultEntity.KW_STATUS_SUCCESS);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

}
