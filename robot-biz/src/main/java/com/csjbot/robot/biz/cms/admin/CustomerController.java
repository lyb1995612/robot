package com.csjbot.robot.biz.cms.admin;



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

import com.csjbot.robot.base.exception.ServiceException;
import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.cms.model.Customer;
import com.csjbot.robot.biz.cms.service.CustomerService;
import com.csjbot.robot.biz.sys.model.SysAttachment;
import com.csjbot.robot.biz.ums.model.User;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Description: 商户信息
 * @author XMT
 * @created 2017年4月13日
 */

@Controller
@RequestMapping(value = "/cms")
public class CustomerController {
	
//	private Logger logger = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * @discription商户列表页
	 * @author XMT
	 * @created 2017年4月13日
	 */
	@RequestMapping(value = "/list")
	public ModelAndView portal(){
		ModelAndView mv = new ModelAndView("cms/customer_list");
		return mv;
	}
	
	/**
	 * @discription 跳转到新增页面
	 * @author XMT
	 * @created 2017年4月15日 下午14:22
	 */
	@RequestMapping(value = "/toCustomerAdd")
	public ModelAndView toCustomerAdd() {
		ModelAndView mv = new ModelAndView("cms/customer_add");
		return mv;
	}	
	
	/**
     * @discription 跳转到修改页面
     * @author XMT      
	 * @throws ServiceException 
     * @created 2017年4月15日 下午14:22
	 */
	@RequestMapping(value = "{code_group}/{code}/toCustomerUpdate")
	public ModelAndView toCustomerUpdate(@PathVariable String code_group,@PathVariable String code) {
		Customer customer = customerService.selectByPrimaryKey(code, code_group); ;
	    ModelAndView mv = new ModelAndView("cms/customer_update");
	    mv.addObject("customer",customer);
	    mv.addObject("editable",1);
	    return mv;
	}
	
	/**
     * @discription 跳转到详情页
     * @author XMT       
     * @created 2017年4月15日 下午14:22
     */
	@RequestMapping(value = "{code_group}/{code}/toCustomerDetail")
	public ModelAndView toCustomerDetail(@PathVariable String code_group,@PathVariable String code) {
		Customer customer = customerService.selectByPrimaryKey(code, code_group);
		ModelAndView mv = new ModelAndView("cms/customer_update");
		mv.addObject("customer", customer);
		mv.addObject("editable", 0);
		return mv;
	}

	/**
	 * 获得所有商户的数据
	 * @param param
	 * @param request
	 * @param builder
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

            String sortString = null;
            if (orderName != null && !"".equals(orderName) && dir != null && !"".equals(dir)) {
                sortString = orderName + "." + dir;
            }
            
            String customer = request.getParameter("customer");
            if (StringUtil.notEmpty(customer)) {
                params.put("customer", customer);
            }
            PageList<Customer> list = customerService.page(params, (start / length) + 1, length, sortString);
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "search success");
            if (list!= null && list.size() > 0) {
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
     * @discription 删除商户
     * @author XMT       
     * @created 2017年4月15日 下午14:22
	 */
	@RequestMapping(value = "{code_group}/{code}/customerDelete")
	public ResponseEntity<String> CustomerDelete(@PathVariable String code_group,@PathVariable String code,HttpServletResponse response){
		JSONObject result = new JSONObject();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    response.setCharacterEncoding("UTF-8");
	    String msg = ResultEntity.KW_STATUS_SUCCESS;
	    try {
	    	customerService.delete(code, code_group);
		} catch (Exception e) {
			msg=e.getMessage();
		}
	    result.put("msg", msg);
	    return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
	
	/**
	 * 新增商户
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> customerAdd(Customer customer,HttpServletRequest request,HttpServletResponse response){
        JSONObject result = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        String id = StringUtil.createUUID();
        SysAttachment attach = new SysAttachment();
        attach.setTransaction_id(id);
        attach.setTransaction_type(Constants.Attachment.Type.CUSTOMER_BASIC_INFO);
        attach.setOwner_fk(loginUser.getId());
        attach.setCreator_fk(loginUser.getId());
        attach.setUpdater_fk(loginUser.getId());
        attach.setSort(0);
        String msg = "";
        if(customerService.insert(customer) > 0){
        	msg = ResultEntity.KW_STATUS_SUCCESS;
        }
        result.put("msg", msg);
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
	 
	/**
     * @discription 修改商户
     * @author XMT      
     * @created 2017年4月15日 下午14:22
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> customerUpdate(Customer customer,HttpServletRequest request,HttpServletResponse response){
	    JSONObject result = new JSONObject();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    response.setCharacterEncoding("UTF-8");
	    User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
	    String msg = "";
	    SysAttachment attach = new SysAttachment();
	    attach.setTransaction_type(Constants.Attachment.Type.CUSTOMER_BASIC_INFO);
	    attach.setUpdater_fk(loginUser.getId());
	    if(customerService.update(customer) > 0){
	    	msg = ResultEntity.KW_STATUS_SUCCESS;
	    }
	    result.put("msg", msg);
	    return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
}
