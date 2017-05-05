package com.csjbot.robot.biz.scs.admin;


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

import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.scs.model.ScsDesk;
import com.csjbot.robot.biz.scs.service.ScsService;
import com.csjbot.robot.biz.ums.model.User;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * Description: 桌号信息
 * @author XMT
 * @created 2017年4月17日
 */

@Controller
@RequestMapping("/scs")
public class DeskController {
	
	@Autowired
	private ScsService scsService;
	
	/**
	 * @discription桌号列表页
	 * @author XMT
	 * @created 2017年4月17日
	 */
	@RequestMapping("/list")
	public ModelAndView protal(){
		ModelAndView mv = new ModelAndView("/scs/desk_list");
		return mv;
	}


	/**
	 * @discription 跳转到桌号新增页面
	 * @author XMT       
	 * @created 2017年4月17日
	 */
	@RequestMapping(value = "/toDeskAdd")
	public ModelAndView toDeskAdd() {
		ModelAndView mv = new ModelAndView("scs/desk_add");
		return mv;
	}
	
	/**
	 * @discription 跳转到桌号详情页
	 * @author XMT       
	 * @created 2017年4月17日
	 */
	@RequestMapping(value = "{id}/toDeskDetail")
	public ModelAndView toDeskDetail(@PathVariable String id) {
		ScsDesk desk = scsService.selectByPrimaryKey(id);
		ModelAndView mv = new ModelAndView("scs/desk_detail");
		mv.addObject("desk",desk);
		return mv;
	}
	
	/**
	 * @discription 新增桌号
	 * @author XMT       
	 * @created 2017年4月17日
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> deskAdd(ScsDesk scsDesk, HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		response.setCharacterEncoding("UTF-8");
		User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
		String id = StringUtil.createUUID();
		scsDesk.setId(id);
		scsDesk.setCreator_fk(loginUser.getId());
		scsDesk.setUpdater_fk(loginUser.getId());
		String msg = "";
		if(scsService.insert(scsDesk) > 0){
			msg = ResultEntity.KW_STATUS_SUCCESS;
		}
		result.put("msg", msg);
		return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
	/**
     * @discription 删除桌号
     * @author XMT     
     * @created 2017年4月17日
	 */
	@RequestMapping(value = "{id}/deskDelete")
	public ResponseEntity<String> deskDelete(@PathVariable String id,HttpServletResponse response){
		JSONObject result = new JSONObject();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    response.setCharacterEncoding("UTF-8");
	    String msg = ResultEntity.KW_STATUS_SUCCESS;
	    try {
	    	scsService.delete(id);
		} catch (Exception e) {
			msg=e.getMessage();
		}
	    result.put("msg", msg);
	    return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
	
	  /**
	    * 查找全部桌号列表
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

            String number = request.getParameter("number");
            if (StringUtil.notEmpty(number)) {
                params.put("number", number);
            }
            String sortString = null;
            if (orderName != null && !"".equals(orderName) && dir != null && !"".equals(dir)) {
                sortString = orderName + "." + dir;
            }
            PageList<ScsDesk> list = scsService.page(params, (start / length) + 1, length, sortString);
  //          Page<Map<String, Object>> pageMap = scsService.pageAndSort(params, (start / length) + 1, length, sortString);
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "search success");
            if (list != null && list.size() > 0) {
                result.addObject("data", list);
                result.addObject("recordsFiltered", list.size() );
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
