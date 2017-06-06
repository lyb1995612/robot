package com.csjbot.robot.admin.controller.scs;

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
import org.springframework.web.servlet.ModelAndView;

import com.csjbot.robot.base.page.Page;
import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.scs.model.ScsAccessory;
import com.csjbot.robot.biz.scs.model.ScsShop;
import com.csjbot.robot.biz.scs.service.ScsService;
import com.csjbot.robot.biz.sys.model.SysAttachment;
import com.csjbot.robot.biz.sys.service.SysAttachService;
import com.csjbot.robot.biz.ums.model.User;
import com.csjbot.robot.biz.util.FileUtil;

/**
 * 
 * @author Zhangyangyang
 * 
 */
@Controller
@RequestMapping("/acce")
public class AccessoryController {
	
	private Logger logger = Logger.getLogger(AccessoryController.class);
	
	@Autowired
	private ScsService scsService;
	
	@Autowired
	private SysAttachService attachService;
	
	/**
	 * @discription菜品列表
	 * @author XMT
	 * @created 2017�?4�?17�?
	 */
	@RequestMapping("/list")
	public ModelAndView protal(){
		ModelAndView mv = new ModelAndView("/scs/accessory_list");
		return mv;
	}


	/**
	 * @discription 跳转菜品新增页面
	 * @author XMT       
	 * @created 2017�?4�?17�?
	 */
	@RequestMapping(value = "/toAcceAdd")
	public ModelAndView toDeskAdd() {
		ModelAndView mv = new ModelAndView("scs/accessory_add");
		List<ScsShop> shopList=scsService.selectShopAll();
		mv.addObject("shop_list", shopList);
		return mv;
	}
	
	/**
	 * @discription 跳转到菜品详情页
	 * @author XMT       
	 * @created 2017�?4�?17�?
	 */
	@RequestMapping(value = "{id}/toAcceDetail")
	public ModelAndView toDeskDetail(@PathVariable String id) {
		ScsAccessory scsAccessory = scsService.selectAccessoryByPrimaryKey(id);
		SysAttachment sysAttachment = attachService.getAttachByTransInfo(scsAccessory.getId(), Constants.Attachment.Type.SC_ACCESSORY);
		ModelAndView mv = new ModelAndView("scs/accessory_detail");
		mv.addObject("acce",scsAccessory);
		mv.addObject("file",sysAttachment);
		mv.addObject("location","/attach/"+scsAccessory.getId()+"/"+Constants.Attachment.Type.SC_ACCESSORY+"/pic");
		 mv.addObject("editable",0);
		return mv;
	}
	/**
	 * 查找全部菜品列表
	 * 
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

			String name = request.getParameter("name");
			if (StringUtil.notEmpty(name)) {
				params.put("name", name);
			}
			String sortString = null;
			if (orderName != null && !"".equals(orderName) && dir != null && !"".equals(dir)) {
				sortString = orderName + "." + dir;
			}
			Page<Map<String, Object>> pageMap = scsService.pageAccessory(params, (start / length) + 1, length, sortString);
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "search success");
            if (pageMap.getRows() != null && pageMap.getRows().size() > 0) {
                result.addObject("data", pageMap.getRows());
                result.addObject("recordsFiltered", pageMap.getTotal());
                result.addObject("recordsTotal", pageMap.getTotal());
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
	 * @discription 新增菜品
	 * @author XMT
	 * @created 2017�?4�?17�?
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> deskAdd(ScsAccessory scsAccessory,  @RequestParam(required = false) MultipartFile acce_file,HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        FileUtil fileUtil = new FileUtil();
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        String id = StringUtil.createUUID();
        scsAccessory.setId(id);
        scsAccessory.setCreator_fk(loginUser.getId());
        scsAccessory.setUpdater_fk(loginUser.getId());
        
        String msg = "";
        String photo_url = null;
        SysAttachment attach = new SysAttachment();
        attach.setTransaction_id(id);
        attach.setTransaction_type(Constants.Attachment.Type.SC_ACCESSORY);
        attach.setOwner_fk(loginUser.getId());
        attach.setCreator_fk(loginUser.getId());
        attach.setUpdater_fk(loginUser.getId());
        attach.setMemo(scsAccessory.getMemo());
        attach.setSort(0);
        if (acce_file != null) {
 //SysAttachService attachService, SysAttachment attach,String type, MultipartFile file, String dir, String foldername
//	SysAttachment attach,String type, MultipartFile file, String dir, String foldername
        	photo_url = fileUtil.uploadAndModifyAttach(attachService,attach,Constants.Attachment.Type.SC_ACCESSORY, acce_file, Constants.UPLOAD_PATH, Constants.Attachment.Path.SC_ACCESSORY_FILE_PATH);
	        if ("error".equals(photo_url)) {
	        	msg = ResultEntity.KW_STATUS_FAIL;
	        	result.put("msg", msg);
	        	logger.error("Accessory upload picture error!");
	            return new ResponseEntity<String>(result.toString() ,headers, HttpStatus.OK);
	        }
        }
        if(scsService.insertAccessory(scsAccessory) > 0){
        	msg = ResultEntity.KW_STATUS_SUCCESS;
        }
        result.put("msg", msg);
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

	/**
	 * @discription 删除菜品
	 * @author XMT
	 * @created 2017�?4�?17�?
	 */
	@RequestMapping(value = "{id}/acceDelete")
	public ResponseEntity<String> deskDelete(@PathVariable String id, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		String msg = ResultEntity.KW_STATUS_SUCCESS;
		ScsAccessory scsAccessory = scsService.selectAccessoryByPrimaryKey(id);
		try {
			attachService.deleteByTransInfo(scsAccessory.getId(), Constants.Attachment.Type.SC_ACCESSORY);
			scsService.deleteAccessory(id);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
}
