package com.csjbot.robot.admin.controller.pms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csjbot.robot.biz.base.entity.ResultEntity;
import com.csjbot.robot.biz.base.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.base.util.StringUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.cms.model.CmsRobot;
import com.csjbot.robot.biz.pms.model.PmsProductDistribute;
import com.csjbot.robot.biz.pms.model.Pms_product;
import com.csjbot.robot.biz.pms.service.PmsService;
import com.csjbot.robot.biz.ums.model.User;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**          
 * Description: 产品分配
 * @author lyb       
 * @created 2017-05-10 下午16:45:27    
 */
@Controller
@RequestMapping("/pd")
public class ProductDistributeController {
			
	@Autowired
	private PmsService pmsService;
	
	/**
	     * @discription 产品列表�?
	     * @author CJay       
	     * @created 2017�?3�?23�? 上午11:03:59
	 */
	@RequestMapping(value = "/list")
    public ModelAndView portal() {
        ModelAndView mv = new ModelAndView("pms/product_distribute_list");
        return mv;
    }
	
	/**
	     * @discription 跳转到新增页�?
	     * @author CJay       
	     * @created 2017�?3�?23�? 上午11:03:42
	 */
	@RequestMapping(value = "/toProduct_distributeAdd")
    public ModelAndView toProductAdd(HttpServletRequest request) {
		List<CmsRobot> cmsRobots = pmsService.getCmsRobots();
		List<Pms_product> pms_products=pmsService.selectAll();
		request.setAttribute("cmsRobots", cmsRobots);
		request.setAttribute("pms_products", pms_products);
        ModelAndView mv = new ModelAndView("pms/product_distribute_add");
        return mv;
    }	
	
	/**
     * @discription 跳转到修改页�?
     * @author lyb       
     * @created 2017�?5�?15�? 下午13:56:42
	 */
	@RequestMapping(value = "{sn}/toProduct_distributeUpdate")
	public ModelAndView toProducUpdate(@PathVariable String sn) {
		List<Pms_product> pms_products=pmsService.selectAll();
		List<String> product_fks=pmsService.selectProductBySn(sn);
		for (Pms_product pms_product : pms_products) {
			for (int i=0;i<product_fks.size();i++) {
				if (pms_product.getId().equals(product_fks.get(i))) {
					pms_product.setChecked(true);
					product_fks.remove(i);
					break;
				}
			}
		}
	    ModelAndView mv = new ModelAndView("pms/product_distribute_update");
	    mv.addObject("sn",sn);
	    mv.addObject("pms_products",pms_products);	    
	    return mv;
	}	
	
	/**
	     * @discription 新增产品分配
	     * @author CJay       
	     * @created 2017�?3�?23�? 上午11:03:26
	 */		
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> productAdd(@RequestBody List<String> product_ids, HttpServletRequest request,HttpServletResponse response){
        JSONObject result = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);      
        String msg = "";
        String sn=product_ids.get(product_ids.size()-1);
        product_ids.remove(product_ids.size()-1);
        List<PmsProductDistribute> pmsProductDistributes=new ArrayList<PmsProductDistribute>();
        for (String product_id : product_ids) {
        	PmsProductDistribute pmsProductDistribute=new PmsProductDistribute();
            String id = StringUtil.createUUID();
            pmsProductDistribute.setId(id);
            pmsProductDistribute.setSn(sn);
            pmsProductDistribute.setProduct_fk(product_id);
            pmsProductDistribute.setCreator_fk(loginUser.getId());
            pmsProductDistribute.setUpdater_fk(loginUser.getId());
            pmsProductDistributes.add(pmsProductDistribute);
		}
        if(pmsService.insert(pmsProductDistributes)){
        	msg = ResultEntity.KW_STATUS_SUCCESS;
        }
        
        result.put("msg", msg);
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

	/**
     * @discription 修改产品分配
     * @author CJay       
     * @created 2017�?3�?23�? 上午11:03:26
	 */
	@Transactional
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> productUpdate(@RequestBody List<String> product_ids,HttpServletRequest request,HttpServletResponse response){
       
		JSONObject result = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);      
        String msg = ResultEntity.KW_STATUS_FAIL;
        String sn=product_ids.get(product_ids.size()-1);
        product_ids.remove(product_ids.size()-1);
        if (!pmsService.deletePdByPrimaryKey(sn)) {
	//		msg=ResultEntity.KW_STATUS_FAIL;
		}else {
			List<PmsProductDistribute> pmsProductDistributes=new ArrayList<PmsProductDistribute>();
	        for (String product_id : product_ids) {
	        	PmsProductDistribute pmsProductDistribute=new PmsProductDistribute();
	            String id = StringUtil.createUUID();       
	            pmsProductDistribute.setId(id);
	            pmsProductDistribute.setSn(sn);
	            pmsProductDistribute.setProduct_fk(product_id);
	            pmsProductDistribute.setCreator_fk(loginUser.getId());
	            pmsProductDistribute.setUpdater_fk(loginUser.getId());
	            pmsProductDistributes.add(pmsProductDistribute);
			}
            if(pmsService.insert(pmsProductDistributes)){
            	msg = ResultEntity.KW_STATUS_SUCCESS;
            }else {
            	throw new RuntimeException();
			}
		}
  
        result.put("msg", msg);
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);

	}
	
	/**
     * @discription 删除产品分配
     * @author CJay       
     * @created 2017�?3�?24�? 上午8:52:41
	 */
	@RequestMapping(value = "{sn}/productDistributeDelete")
	public ResponseEntity<String> ProductDelete(@PathVariable String sn,HttpServletResponse response){
		JSONObject result = new JSONObject();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    response.setCharacterEncoding("UTF-8");
	    String msg = "删除失败！";
		if (pmsService.deletePdByPrimaryKey(sn)) {
			msg = ResultEntity.KW_STATUS_SUCCESS;
		}	 	    
	    result.put("msg", msg);
	    return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

	/**
     * @discription 搜索产品分配
     * @author CJay       
     * @created 2017�?3�?24�? 上午8:52:41
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
            PageList<PmsProductDistribute> list = pmsService.disPage(params, (start / length) + 1, length, sortString);
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
