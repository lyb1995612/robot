package com.csjbot.robot.admin.controller.scs;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csjbot.robot.biz.base.util.StringUtil;
import com.csjbot.robot.biz.base.entity.ResultEntity;
import com.csjbot.robot.biz.base.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.scs.model.ScsDishType;
import com.csjbot.robot.biz.scs.service.ScsService;
import com.csjbot.robot.biz.ums.model.User;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 
 * @author Zhangyangyang
 * 
 */
@Controller
@RequestMapping("/dte")
public class DishTypeController {
	@Autowired
	private ScsService scsService;
	
	
	/**
	 * @discription菜品列表
	 * @author XMT
	 * @created 2017�?4�?17�?
	 */
	@RequestMapping("/list")
	public ModelAndView protal(){
		ModelAndView mv = new ModelAndView("/scs/dishType_list");
		return mv;
	}


	/**
	 * @discription 跳转菜品新增页面
	 * @author XMT       
	 * @created 2017�?4�?17�?
	 */
	@RequestMapping(value = "/toDishTypeAdd")
	public ModelAndView toDeskAdd() {
		ModelAndView mv = new ModelAndView("scs/dishType_add");
		return mv;
	}
	
	/**
	 * @discription 跳转到菜品详情页
	 * @author XMT       
	 * @created 2017�?4�?17�?
	 */
	@RequestMapping(value = "{id}/toDishTypeDetail")
	public ModelAndView toDeskDetail(@PathVariable Integer id) {
		ScsDishType scsDishType = scsService.selectDishTypeByPrimaryKey(id);
		ModelAndView mv = new ModelAndView("scs/dishType_detail");
		mv.addObject("dish_type",scsDishType);
		mv.addObject("location","/attach/"+scsDishType.getId()+"/"+Constants.Attachment.Type.DESK_BASIC_INFO+"/pic");
		mv.addObject("editable",0);
		return mv;
	}
	/**
     * @discription 跳转到修改页�?
     * @author CJay       
     * @created 2017�?3�?23�? 上午11:03:42
	 */
	@RequestMapping(value = "{id}/toDishTypeUpdate")
	public ModelAndView toProducUpdate(@PathVariable Integer id) {
	    ModelAndView mv = new ModelAndView("scs/dishType_detail");
	    ScsDishType scsDishType = scsService.selectDishTypeByPrimaryKey(id);
	    mv.addObject("dish_type", scsDishType);
	    mv.addObject("location","/attach/"+scsDishType.getId()+"/"+Constants.Attachment.Type.DISH_PIC+"/pic");
	    mv.addObject("editable",1);
	    return mv;
	}
	/**
	 * @discription 新增菜品类型
	 * @author XMT
	 * @created 2017�?4�?17�?
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> deskAdd(ScsDishType scsDishType, HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
		scsDishType.setCreator_fk(loginUser.getId());
		scsDishType.setUpdater_fk(loginUser.getId());
		String msg = "";
		if (scsService.insertDishType(scsDishType) > 0) {
			msg = ResultEntity.KW_STATUS_SUCCESS;
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

	/**
	 * @discription 删除菜品类型
	 * @author XMT
	 * @created 2017�?4�?17�?
	 */
	@RequestMapping(value = "{id}/dishTypeDelete")
	public ResponseEntity<String> deskDelete(@PathVariable Integer id, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		String msg = ResultEntity.KW_STATUS_SUCCESS;
		try {
			scsService.deleteDishType(id);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

	/**
     * @discription 修改产品
     * @author CJay       
     * @created 2017�?3�?23�? 上午11:03:26
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> productUpdate(ScsDishType scsDishType,HttpServletRequest request,HttpServletResponse response){
	    JSONObject result = new JSONObject();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    response.setCharacterEncoding("UTF-8");
	    User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
	    scsDishType.setUpdater_fk(loginUser.getId());
	    String msg = "";
	    if(scsService.updateDishType(scsDishType) > 0){
	    	msg = ResultEntity.KW_STATUS_SUCCESS;
	    }
	    result.put("msg", msg);
	    return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
	
	/**
	 * 查找全部菜品类型
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

			String type_name = request.getParameter("type_name");
			if (StringUtil.notEmpty(type_name)) {
				params.put("type_name", type_name);
			}
			String sortString = null;
			if (orderName != null && !"".equals(orderName) && dir != null && !"".equals(dir)) {
				sortString = orderName + "." + dir;
			}
			PageList<ScsDishType> list = scsService.pageDishType(params, (start / length) + 1, length, sortString);
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
