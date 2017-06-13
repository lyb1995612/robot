package com.csjbot.robot.admin.controller.scs;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.csjbot.robot.biz.cms.model.RobotGroupParam;
import com.csjbot.robot.biz.cms.service.CmsRobotService;
import com.csjbot.robot.biz.scs.model.ScsDishLink;
import com.csjbot.robot.biz.scs.service.ScsService;
import com.csjbot.robot.biz.sys.model.SysDataDictionary;
import com.csjbot.robot.biz.ums.model.User;

/**
 * @explain 菜品绑定
 * @author  AlexZhang
 * @date    2017年5月24日
 * @company PangolinRobot
 */
@Controller
@RequestMapping("dsn")
public class DishLinkController {

	@Autowired
	private ScsService scsService;

	@Autowired
	private CmsRobotService cmsRobotService;

	/**
	 * 初始化菜品绑定界面
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView portal() {
		ModelAndView mv = new ModelAndView("/scs/dish_sn_list");
		return mv;
	}
	
	/**
	@RequestMapping(value = "{id}/toDishSNUpdate")
	public ModelAndView toProducUpdate(@PathVariable String id) {
		ScsDishLink scsDishLink = scsService.selectByPK(id);
		ModelAndView mv = new ModelAndView("scs/dishSN_detail");
		mv.addObject("select_style", "block");
		mv.addObject("input_style", "none");
		mv.addObject("sn", scsDishLink);
		mv.addObject("location", "/attach/" + scsDishLink.getId() + "/" + Constants.Attachment.Type.DISH_PIC + "/pic");
		mv.addObject("editable", 1);
		return mv;
	}*/
	 
	/**
	 * 初始化关联菜品界面
	 * @param sn
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/{sn}/toDishSNRef")
	public ModelAndView assignPermission(@PathVariable String sn, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<ScsDishLink> scsDishLink = null;
		ModelAndView mv = new ModelAndView("scs/dish_sn_ref");
		if (StringUtil.notEmpty(sn)) {
			scsDishLink = scsService.selectBySN(sn);
			if (scsDishLink == null) {
				response.sendError(HttpStatus.NOT_FOUND.value());
				return null;
			}
		} else {
			response.sendError(HttpStatus.NOT_FOUND.value());
			return null;
		}
		List<SysDataDictionary> cplist = scsService.findDictionaryByCode(Constants.DataDictionary.CPFL);
		mv.addObject("cplist", cplist);
		mv.addObject("scsDishLink", scsDishLink);
		int relevance_num = scsService.countDishLinkSize(sn);
		mv.addObject("relevance_num", relevance_num);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sn", sn);
		List<Map<String, Object>> dish = scsService.listDish(params);
		mv.addObject("dish", dish);
		return mv;
	}
	
	/**
	 * 获取菜品绑定信息
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
			if (StringUtil.notEmpty(sn)) {
				params.put("sn", sn);
			}
			String sortString = null;
			if (orderName != null && !"".equals(orderName) && dir != null && !"".equals(dir)) {
				sortString = orderName + "." + dir;
			}
			Page<Map<String, Object>> pageMap = cmsRobotService.page(params, (start / length) + 1, length, sortString);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "search success");
			if (pageMap.getRows() != null) {
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

	@RequestMapping(value = "/{id}/dishLink/search")
	public ResponseEntity<ResultEntity> searchUser(@PathVariable String id, @RequestBody RobotGroupParam param,
			HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder builder) {
		ResultEntity result = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", id);
			params.put("sn", param.getSn());
			List<Map<String, Object>> dish = scsService.listDish(params);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "Success!", dish);
		} catch (Exception e) {
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/dsn/{id}/dishLink/search").buildAndExpand(id).toUri());
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{sn}/dishLink/save")
	public ResponseEntity<ResultEntity> saveRolePermission(@PathVariable String sn, HttpServletRequest request,
			HttpServletResponse response, UriComponentsBuilder builder) throws IOException {
		ResultEntity result = null;
		User currentUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
		try {
			List<ScsDishLink> scsDishLinks = scsService.selectBySN(sn);
			for (ScsDishLink scsDishLink : scsDishLinks) {
				if (scsDishLink == null) {
					response.sendError(HttpStatus.NOT_FOUND.value());
				}
			}
			ScsDishLink link = null;
			int totalSize = Integer.parseInt(request.getParameter("dishLink-totalSize"));
			for (int i = 0; i < totalSize; i++) {
				link = (ScsDishLink) RequestUtil.fetchParameter(ScsDishLink.class, i, request);
				link.setId(sn);
				link.setCreator_fk(currentUser.getId());
				link.setUpdater_fk(currentUser.getId());
				if (link.isChecked() == false) {
					ScsDishLink dishLink = scsService.getDish(link.getId());
					if (dishLink != null) {
						link.setValidT(false);
					} else {
						continue;
					}
				}
				scsService.saveOrUpdate(link);
			}
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "Success!");

		} catch (Exception e) {
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/sdn/{sn}/dishLink/save").buildAndExpand(sn).toUri());
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}
}
