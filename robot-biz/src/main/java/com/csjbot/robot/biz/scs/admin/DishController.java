package com.csjbot.robot.biz.scs.admin;

import java.util.HashMap;
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

import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.scs.model.ScsDish;
import com.csjbot.robot.biz.scs.model.ScsDishType;
import com.csjbot.robot.biz.scs.service.ScsService;
import com.csjbot.robot.biz.sys.model.SysAttachment;
import com.csjbot.robot.biz.sys.service.SysAttachService;
import com.csjbot.robot.biz.ums.model.User;
import com.csjbot.robot.biz.util.FileUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 
 * @author Zhangyangyang
 * 
 */
@Controller
@RequestMapping("/dish")
public class DishController {

	private Logger logger = Logger.getLogger(DishController.class);

	@Autowired
	private ScsService scsService;

	@Autowired
	private SysAttachService attachService;

	/**
	 * @discription菜品列表
	 * @author XMT
	 * @created 2017年4月17日
	 */
	@RequestMapping("/list")
	public ModelAndView protal() {
		ModelAndView mv = new ModelAndView("/scs/dish_list");
		return mv;
	}

	/**
	 * @discription 跳转菜品新增页面
	 * @author XMT
	 * @created 2017年4月17日
	 */
	@RequestMapping(value = "/toDishAdd")
	public ModelAndView toDeskAdd() {
		ModelAndView mv = new ModelAndView("scs/dish_add");
		java.util.List<ScsDishType> list = scsService.selectAll();
		mv.addObject("dish_type_list", list);
		return mv;
	}
	/**
     * @discription 跳转到修改页面
     * @author CJay       
     * @created 2017年3月23日 上午11:03:42
	 */
	@RequestMapping(value = "{id}/toDishUpdate")
	public ModelAndView toProducUpdate(@PathVariable String id) {
		ScsDish scsDish = scsService.selectDisByPrimaryKey(id);
	    ModelAndView mv = new ModelAndView("scs/dish_detail");
	    ScsDishType scsDishType = scsService.selectDishTypeByPrimaryKey(scsDish.getDish_type());
		java.util.List<ScsDishType> list = scsService.selectAll();
		mv.addObject("dish_type_list", list);
		mv.addObject("select_style", "block");
		mv.addObject("input_style", "none");
	    mv.addObject("dish_type", scsDishType);
	    mv.addObject("dish",scsDish);
	    mv.addObject("location","/attach/"+scsDish.getId()+"/"+Constants.Attachment.Type.DISH_PIC+"/pic");
	    mv.addObject("editable",1);
	    return mv;
	}

	/**
	 * @discription 跳转到菜品详情页
	 * @author XMT
	 * @created 2017年4月17日
	 */
	@RequestMapping(value = "{id}/toDishDetail")
	public ModelAndView toDeskDetail(@PathVariable String id) {
		ScsDish scsDish = scsService.selectDisByPrimaryKey(id);
		ModelAndView mv = new ModelAndView("scs/dish_detail");
		ScsDishType scsDishType = scsService.selectDishTypeByPrimaryKey(scsDish.getDish_type());
		java.util.List<ScsDishType> list = scsService.selectAll();
		mv.addObject("dish_type_list", list);
		mv.addObject("dish_type", scsDishType);
		mv.addObject("select_style", "none");
		mv.addObject("input_style", "block");
		mv.addObject("dish", scsDish);
		mv.addObject("location","/attach/" + scsDish.getId() + "/" + Constants.Attachment.Type.DISH_PIC + "/pic");
		mv.addObject("editable",0);
		return mv;
	}

	/**
	 * @discription 新增菜品
	 * @author XMT
	 * @created 2017年4月17日
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> deskAdd(ScsDish scsDish,  @RequestParam(required = false) MultipartFile photo,HttpServletRequest request,HttpServletResponse response) {
		JSONObject result = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        FileUtil fileUtil = new FileUtil();
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        String id = StringUtil.createUUID();
        scsDish.setId(id);
        scsDish.setCreator_fk(loginUser.getId());
        scsDish.setUpdater_fk(loginUser.getId());
        
        String msg = "";
        String photo_url = null;
        SysAttachment attach = new SysAttachment();
        attach.setTransaction_id(id);
        attach.setTransaction_type(Constants.Attachment.Type.DISH_PIC);
        attach.setOwner_fk(loginUser.getId());
        attach.setCreator_fk(loginUser.getId());
        attach.setUpdater_fk(loginUser.getId());
        attach.setMemo(scsDish.getMemo());
        attach.setSort(0);
        if (photo != null) {
        	photo_url = fileUtil.uploadAndModifyAttach(attachService,attach, photo, Constants.UPLOAD_PATH, Constants.Attachment.Path.SC_ACCESSORY_FILE_PATH);
	        if ("error".equals(photo_url)) {
	        	msg = ResultEntity.KW_STATUS_FAIL;
	        	result.put("msg", msg);
	        	logger.error("Product upload picture error!");
	            return new ResponseEntity<String>(result.toString() ,headers, HttpStatus.OK);
	        }
        }
        ScsDish dish = scsService.selectDisByName(scsDish.getName());
        if (dish != null) {
        	msg = ResultEntity.KW_STATUS_SUCCESS+"exit";
		}else {
			if(scsService.insertDish(scsDish) > 0){
	        	msg = ResultEntity.KW_STATUS_SUCCESS;
	        }
		}
        result.put("msg", msg);
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

	/**
	 * @discription 删除菜品
	 * @author XMT
	 * @created 2017年4月17日
	 */
	@RequestMapping(value = "{id}/dishDelete")
	public ResponseEntity<String> deskDelete(@PathVariable String id, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		String msg = ResultEntity.KW_STATUS_SUCCESS;
		ScsDish scsDish = scsService.selectDisByPrimaryKey(id);
		try {
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%");
			attachService.deleteByTransInfo(scsDish.getId(), Constants.Attachment.Type.DISH_PIC);
			scsService.deleteDish(id);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
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
			PageList<ScsDish> list = scsService.pageDish(params, (start / length) + 1, length, sortString);
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
	/**
     * @discription 修改菜品
     * @author CJay       
     * @created 2017年3月23日 上午11:03:26
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> dishUpdate(ScsDish scsDish, HttpServletRequest request,HttpServletResponse response) {
		System.out.println(111111);
		JSONObject result = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        FileUtil fileUtil = new FileUtil();
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        String id = StringUtil.createUUID();
        scsDish.setUpdater_fk(loginUser.getId());
        
        String msg = "";
        String photo_url = null;
        SysAttachment attach = new SysAttachment();
        attach.setTransaction_id(scsDish.getId());
        attach.setTransaction_type(Constants.Attachment.Type.DISH_PIC);
        attach.setUpdater_fk(loginUser.getId());
        attach.setMemo(scsDish.getMemo());
        if (request instanceof MultipartHttpServletRequest) {
        	MultipartFile photo =  ((MultipartHttpServletRequest) request).getFile("photo");
		    if (photo != null) {
		    	attachService.deleteByTransInfo(attach.getTransaction_id(),attach.getTransaction_type());
		    	photo_url = fileUtil.uploadAndModifyAttach(attachService,attach, photo, Constants.UPLOAD_PATH, Constants.Attachment.Path.SC_ACCESSORY_FILE_PATH);
			    if ("error".equals(photo_url)) {
			    	msg = ResultEntity.KW_STATUS_FAIL;
			    	result.put("msg", msg);
			    	logger.error("Product upload picture error!");
			        return new ResponseEntity<String>(result.toString() ,headers, HttpStatus.OK);
			    }
		    }
        }
        if (scsService.updateDish(scsDish) > 0) {
        	msg = ResultEntity.KW_STATUS_SUCCESS;
		}
        result.put("msg", msg);
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
}
