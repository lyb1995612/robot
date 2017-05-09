package com.csjbot.robot.admin.controller.pms;

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
import com.csjbot.robot.biz.pms.model.PmsProduct;
import com.csjbot.robot.biz.pms.service.PmsService;
import com.csjbot.robot.biz.sys.model.SysAttachment;
import com.csjbot.robot.biz.sys.service.SysAttachService;
import com.csjbot.robot.biz.ums.model.User;
import com.csjbot.robot.biz.util.FileUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**          
 * Description: 产品信息
 * @author CJay       
 * @created 2017�?3�?21�? 上午10:21:27    
 */
@Controller
@RequestMapping("/pms")
public class ProductController {
	
	private Logger logger = Logger.getLogger(ProductController.class);
			
	@Autowired
	private PmsService pmsService;
	
	@Autowired
	private SysAttachService attachService;
	
	/**
	     * @discription 产品列表�?
	     * @author CJay       
	     * @created 2017�?3�?23�? 上午11:03:59
	 */
	@RequestMapping(value = "/list")
    public ModelAndView portal() {
        ModelAndView mv = new ModelAndView("pms/product_list");
        return mv;
    }
	
	/**
	     * @discription 跳转到新增页�?
	     * @author CJay       
	     * @created 2017�?3�?23�? 上午11:03:42
	 */
	@RequestMapping(value = "/toProductAdd")
    public ModelAndView toProductAdd() {
        ModelAndView mv = new ModelAndView("pms/product_add");
        return mv;
    }	
	
	/**
     * @discription 跳转到修改页�?
     * @author CJay       
     * @created 2017�?3�?23�? 上午11:03:42
	 */
	@RequestMapping(value = "{id}/toProductUpdate")
	public ModelAndView toProducUpdate(@PathVariable String id) {
		PmsProduct pmsProduct = pmsService.selectByPrimaryKey(id);
	    ModelAndView mv = new ModelAndView("pms/product_update");
	    mv.addObject("product",pmsProduct);
	    mv.addObject("location","/attach/"+pmsProduct.getId()+"/"+Constants.Attachment.Type.PRODUCT_BASIC_INFO+"/pic");
	    mv.addObject("editable",1);
	    return mv;
	}
	
	/**
	     * @discription 详情�?
	     * @author CJay       
	     * @created 2017�?3�?23�? 下午3:27:48
	 */
	@RequestMapping(value = "{id}/toProductDetail")
	public ModelAndView toProductDetail(@PathVariable String id) {
		PmsProduct pmsProduct = pmsService.selectByPrimaryKey(id);
	    ModelAndView mv = new ModelAndView("pms/product_update");
	    mv.addObject("product",pmsProduct);
	    mv.addObject("location","/attach/"+pmsProduct.getId()+"/"+Constants.Attachment.Type.PRODUCT_BASIC_INFO+"/pic");
	    mv.addObject("editable",0);
	    return mv;
	}	
	
	
	/**
	     * @discription 新增产品
	     * @author CJay       
	     * @created 2017�?3�?23�? 上午11:03:26
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> productAdd(PmsProduct pmsProduct, @RequestParam(required = false) MultipartFile photo,HttpServletRequest request,HttpServletResponse response){
        JSONObject result = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        FileUtil fileUtil = new FileUtil();
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        String id = StringUtil.createUUID();
        pmsProduct.setId(id);
        pmsProduct.setCreator_fk(loginUser.getId());
        pmsProduct.setUpdater_fk(loginUser.getId());
        String msg = "";
        String photo_url = null;
        SysAttachment attach = new SysAttachment();
        attach.setTransaction_id(id);
        attach.setTransaction_type(Constants.Attachment.Type.PRODUCT_BASIC_INFO);
        attach.setOwner_fk(loginUser.getId());
        attach.setCreator_fk(loginUser.getId());
        attach.setUpdater_fk(loginUser.getId());
        attach.setMemo(pmsProduct.getMemo());
        attach.setSort(0);
        if (photo != null) {
        	photo_url = fileUtil.uploadAndModifyAttach(attachService,attach, photo, Constants.UPLOAD_PATH, Constants.Attachment.Path.PRODUCT_PIC_PATH);
	        if ("error".equals(photo_url)) {
	        	msg = ResultEntity.KW_STATUS_FAIL;
	        	result.put("msg", msg);
	        	logger.error("Product upload picture error!");
	            return new ResponseEntity<String>(result.toString() ,headers, HttpStatus.OK);
	        }
        }
        if(pmsService.insert(pmsProduct)){
        	msg = ResultEntity.KW_STATUS_SUCCESS;
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
	public ResponseEntity<String> productUpdate(PmsProduct pmsProduct,HttpServletRequest request,HttpServletResponse response){
	    JSONObject result = new JSONObject();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    response.setCharacterEncoding("UTF-8");
	    FileUtil fileUtil = new FileUtil();
	    User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
	    pmsProduct.setUpdater_fk(loginUser.getId());
	    String msg = "";
	    String photo_url = null;
        SysAttachment attach = new SysAttachment();
        attach.setTransaction_id(pmsProduct.getId());
        attach.setTransaction_type(Constants.Attachment.Type.PRODUCT_BASIC_INFO);
        attach.setUpdater_fk(loginUser.getId());
        attach.setMemo(pmsProduct.getMemo());
        if (request instanceof MultipartHttpServletRequest) {
        	MultipartFile photo =  ((MultipartHttpServletRequest) request).getFile("photo");
		    if (photo != null) {
		    	attachService.deleteByTransInfo(attach.getTransaction_id(),attach.getTransaction_type());
		    	photo_url = fileUtil.uploadAndModifyAttach(attachService,attach, photo, Constants.UPLOAD_PATH, Constants.Attachment.Path.PRODUCT_PIC_PATH);
			    if ("error".equals(photo_url)) {
			    	msg = ResultEntity.KW_STATUS_FAIL;
			    	result.put("msg", msg);
			    	logger.error("Product upload picture error!");
			        return new ResponseEntity<String>(result.toString() ,headers, HttpStatus.OK);
			    }
		    }
        }
	    if(pmsService.update(pmsProduct)){
	    	msg = ResultEntity.KW_STATUS_SUCCESS;
	    }
	    result.put("msg", msg);
	    return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
	
	/**
     * @discription 删除产品
     * @author CJay       
     * @created 2017�?3�?24�? 上午8:52:41
	 */
	@RequestMapping(value = "{id}/productDelete")
	public ResponseEntity<String> ProductDelete(@PathVariable String id,HttpServletResponse response){
		JSONObject result = new JSONObject();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    response.setCharacterEncoding("UTF-8");
	    String msg = ResultEntity.KW_STATUS_SUCCESS;
	    PmsProduct pmsProduct = pmsService.selectByPrimaryKey(id);
	    try {
//	    	attachService.deleteByTransInfo(pmsProduct.getId(),Constants.Attachment.Type.PRODUCT_BASIC_INFO);
//		    pmsService.deleteByPrimaryKey(id);
	    	pmsProduct.setValid(0); //由于有产品订单关联，订单�?要保留改为软删除
	    	pmsService.update(pmsProduct);
		} catch (Exception e) {
			msg=e.getMessage();
		}
	    result.put("msg", msg);
	    return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
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

            String name = request.getParameter("name");
            if (StringUtil.notEmpty(name)) {
                params.put("name", name);
            }
            String sortString = null;
            if (orderName != null && !"".equals(orderName) && dir != null && !"".equals(dir)) {
                sortString = orderName + "." + dir;
            }
            PageList<PmsProduct> list = pmsService.page(params, (start / length) + 1, length, sortString);
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
