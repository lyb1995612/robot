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

import com.csjbot.robot.base.page.Page;
import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.scs.model.ScsShop;
import com.csjbot.robot.biz.scs.service.ScsService;
import com.csjbot.robot.biz.ums.model.User;

/**
 * @explain 商铺信息
 * @author AlexZhang
 * @date 2017年6月5日
 * @company PangolinRobot
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private ScsService scsService;

	/*
	 * 初始化商铺信息管理界面
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listShopURL() {
		return "/scs/shop_list";
	}

	/*
	 * 显示商铺信息
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResultEntity> page(HttpServletRequest request, HttpServletResponse response) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		ResultEntity result = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>(); // 商铺信息参数
			int length = Integer.valueOf(request.getParameter("length")); // 每页记录的长度
			int start = Integer.valueOf(request.getParameter("start")); // 起始页
			String dir = request.getParameter("order[0][dir]"); // asc
			String orderColIndex = request.getParameter("order[0][column]");
			String orderName = request.getParameter("columns[" + orderColIndex + "][data]"); // shop_code
			String shop_name = request.getParameter("shop_name"); // 商铺名称
			String shop_code = request.getParameter("shop_code"); // 商铺编码
			if (StringUtil.notEmpty(shop_code)) {
				params.put("shop_code", shop_code);
			}
			if (StringUtil.notEmpty(shop_name)) {
				params.put("shop_name", shop_name);
			}
			String sortString = null;
			if (orderName != null && !"".equals(orderName) && dir != null && !"".equals(dir)) {
				sortString = orderName + "." + dir;
			}
			Page<Map<String, Object>> pageMap = scsService.shopPage(params, (start / length) + 1, length, sortString);
			result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "search success");
			if (pageMap.getRows() != null ) {
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
		return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	}

	/*
	 * 初始化新增商铺信息界面
	 */
	@RequestMapping(value = "/addURL", method = RequestMethod.GET)
	public String addShopURL() {
		return "/scs/shop_add";
	}

	/*
	 * 新增操作
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> shopAdd(ScsShop shop, HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
		shop.setCreator_fk(loginUser.getId());
		shop.setUpdater_fk(loginUser.getId());
		String msg = "";
		// if(scsService.insertSelectiveShop(shop) > 0){
		if (scsService.insertShop(shop) > 0) {
			msg = ResultEntity.KW_STATUS_SUCCESS;
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

	/*
	 * 删除操作
	 */
	@RequestMapping(value = "{id}/shopDelete")
	public ResponseEntity<String> shopDelete(@PathVariable String id, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		String msg = ResultEntity.KW_STATUS_SUCCESS;
		try {
			scsService.deleteByShopPK(id);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

	/*
	 * 初始化更新商铺信息界面
	 */
	@RequestMapping(value = "{id}/shopUpdate", method = RequestMethod.GET)
	public ModelAndView updateShopURL(@PathVariable String id) {
		ModelAndView mv = new ModelAndView("/scs/shop_update");
		ScsShop shop = scsService.selectByShopPK(id);
		mv.addObject("shop", shop);
		mv.addObject("editable", 1);
		return mv;
	}
	
	/*
	 * 更新操作
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> shopUpdate(ScsShop shop, HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
		shop.setUpdater_fk(loginUser.getId());
		String msg = "";
		if (scsService.updateShopSelective(shop) > 0) {
			msg = ResultEntity.KW_STATUS_SUCCESS;
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

	/*
	 * 商铺信息详情界面
	 */
	@RequestMapping(value = "{id}/shopDetail")
	public ModelAndView detailShopURL(@PathVariable String id) {
		ModelAndView mv = new ModelAndView("/scs/shop_update");
		ScsShop shop = scsService.selectByShopPK(id);
		mv.addObject("shop", shop);
		mv.addObject("editable", 0);
		return mv;
	}
	
}
