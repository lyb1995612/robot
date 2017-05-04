package com.csjbot.robot.biz.scs.admin;


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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.csjbot.robot.base.exception.ServiceException;
import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.cms.model.Customer;
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
    public ResponseEntity<String> save(ScsDesk scsDesk, HttpServletRequest request, UriComponentsBuilder builder){
		JSONObject result = new JSONObject();
		User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
		String id = StringUtil.createUUID();
		scsDesk.setId(id);
		scsDesk.setCreator_fk(loginUser.getId());
		scsDesk.setUpdater_fk(loginUser.getId());
		String msg = "";
		int flag = scsService.insert(scsDesk);
        if(flag>0){
        	msg = ResultEntity.KW_STATUS_SUCCESS;
        }else{
        	msg = ResultEntity.KW_STATUS_FAIL;
        }
        result.put("msg", msg);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/csc/add").buildAndExpand().toUri());
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
	    public ResponseEntity<ResultEntity> page(@PathVariable ScsDesk param,HttpServletRequest request, UriComponentsBuilder builder) {
	    	ResultEntity result = null;
	        try {
	        	PageList<ScsDesk> list = new PageList<ScsDesk>();
	            list = scsService.page(param);
	            if (list != null) {
	                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "success");
	                result.addObject("list", list);
	                result.addObject("totalSize", list.size());
	            } else {
	                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "search fail!");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
	        }
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(builder.path("/scs/search").buildAndExpand().toUri());
	        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
	    }
}
