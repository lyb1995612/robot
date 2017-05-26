package com.csjbot.robot.admin.controller.scs;

import java.io.IOException;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csjbot.robot.base.page.Page;
import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.scs.model.ScsDishSN;
import com.csjbot.robot.biz.scs.service.ScsService;

/**
 * @explain 菜品绑定
 * @author  AlexZhang
 * @date    2017年5月24日
 * @company PangolinRobot
 */
@Controller
@RequestMapping("dsn")
public class DishSNController {
	
	@Autowired
	private ScsService scsService;
	
	@RequestMapping("list")
	public ModelAndView portal(){
		ModelAndView mv = new ModelAndView("/scs/dish_sn_list");
		return mv;
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
			String sn = request.getParameter("sn");
			if (StringUtil.notEmpty(sn)) {
				params.put("sn", sn);
			}
			String sortString = null;
			if (orderName != null && !"".equals(orderName) && dir != null && !"".equals(dir)) {
				sortString = orderName + "." + dir;
			}
			Page<Map<String, Object>> pageMap = scsService.pageDishSN(params, (start / length) + 1, length, sortString);
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
	
	@RequestMapping(value = "/{id}/toDishSNRef")
    public ModelAndView assignPermission(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ScsDishSN scsDishSN = null;
		ModelAndView mv = new ModelAndView("scs/dish_sn_ref");
        /*if (StringUtil.notEmpty(id)) {
        	scsDishSN = scsService.selectByPK(id);
            if (scsDishSN == null) {
                response.sendError(HttpStatus.NOT_FOUND.value());
                return null;
            }
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value());
            return null;
        }*/
        /*
        List<SysDataDictionary> cplist = scsService.findDictionaryByCode(Constants.DataDictionary.CPFL);
		mv.addObject("cplist", cplist);
        mv.addObject("robotGroup", scsDishSN);
        int relevance_num = scsService.countRobotGroupSize(scsDishSN.getId());
        mv.addObject("relevance_num", relevance_num);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("robotGroupId", id);
        List<Map<String, Object>> dishSN = scsService.listRobot(params);
        mv.addObject("dishSN", dishSN);
        */
        return mv;
    }
	
	@RequestMapping(value = "{id}/toDishSNUpdate")
	public ModelAndView toProducUpdate(@PathVariable String id) {
		ScsDishSN scsDishSN = scsService.selectByPK(id);
	    ModelAndView mv = new ModelAndView("scs/dishSN_detail");
		mv.addObject("select_style", "block");
		mv.addObject("input_style", "none");
	    mv.addObject("sn",scsDishSN);
	    mv.addObject("location","/attach/"+scsDishSN.getId()+"/"+Constants.Attachment.Type.DISH_PIC+"/pic");
	    mv.addObject("editable",1);
	    return mv;
	}
}
