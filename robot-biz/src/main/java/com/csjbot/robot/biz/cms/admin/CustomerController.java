package com.csjbot.robot.biz.cms.admin;



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
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.cms.model.Customer;
import com.csjbot.robot.biz.cms.service.CustomerService;
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
	@RequestMapping(value = "{code}/{code_group}/toCustomerUpdate")
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
	@RequestMapping(value = "{code}/{code_group}/toCustomerDetail")
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
    public ResponseEntity<ResultEntity> page(@RequestBody Customer param,HttpServletRequest request, UriComponentsBuilder builder) {
		ResultEntity result = null;
        try {
//        	Map<String, Object> params = new HashMap<String, Object>();
        	PageList<Customer> list = new PageList<Customer>();
        	list = customerService.page(param);
        	if (list != null) {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "success");
                result.addObject("list", list);
                result.addObject("totalSize", list.size());
            } else {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "search fail!");
            }
/*        	String customer = request.getParameter("customer");
            if (StringUtil.notEmpty(customer)) {
                params.put("customer", customer);
            }
*/        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/cms/search").buildAndExpand().toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
     }
	
	
	/**
     * @discription 删除商户
     * @author XMT       
     * @created 2017年4月15日 下午14:22
	 */
	@RequestMapping(value = "{code}/{code_group}/customerDelete")
	public ResponseEntity<String> CustomerDelete(@PathVariable String code,@PathVariable String code_group,HttpServletResponse response){
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
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<String> save(Customer customer, HttpServletRequest request, UriComponentsBuilder builder) {
		JSONObject result = new JSONObject();
        String msg = "";
        int flag = customerService.insert(customer);
        if(flag > 0){
        	msg = ResultEntity.KW_STATUS_SUCCESS;
        }else{
        	msg = ResultEntity.KW_STATUS_FAIL;
        }
        result.put("msg", msg);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/cms/save").buildAndExpand().toUri());
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
    }
	 
	/**
     * @discription 修改商户
     * @author XMT      
     * @created 2017年4月15日 下午14:22
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<String> edit(Customer customer, HttpServletRequest request, UriComponentsBuilder builder) {
		JSONObject result = new JSONObject();
		int flag = customerService.update(customer);
		String msg = "";
        if(flag>0){
        	msg = ResultEntity.KW_STATUS_SUCCESS;
        }else{
        	msg = ResultEntity.KW_STATUS_FAIL;
        }
        result.put("msg", msg);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/cms/edit").buildAndExpand().toUri());
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
    }
}
